package com.scott.controllers;

import com.scott.models.Music;
import com.scott.models.Soundtrack;
import com.scott.repositories.MusicRepository;
import com.scott.repositories.SoundtrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/soundtracks")
@CrossOrigin(origins="http://localhost:3000")
public class SoundtrackController {
    @Autowired
    SoundtrackRepository soundtrackRepository;

    @Autowired
    MusicRepository musicRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Soundtrack createSoundtrack(@RequestBody Soundtrack soundtrack){
        return soundtrackRepository.save(soundtrack);
    }
    @GetMapping
    public List<Soundtrack> findAllSoundtrack(){
        return soundtrackRepository.findAll();
    }
    @GetMapping("/{soundtrackId}")
    public Soundtrack findSoundtrackById(@PathVariable Long soundtrackId){
        return soundtrackRepository.findById(soundtrackId).orElseThrow(()->new RuntimeException());
    }
    @PutMapping("/{soundtrackId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Soundtrack updateSoundtrack(@PathVariable Long soundtrackId, @RequestBody Soundtrack soundtrack){
        Soundtrack updateS=this.findSoundtrackById(soundtrackId);
        updateS.setName(soundtrack.getName());
        updateS.setDescription(soundtrack.getDescription());
        //updateS.setMusics(soundtrack.getMusics());
        return soundtrackRepository.save(updateS);
    }
    @DeleteMapping("/{soundtrackId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSoundtrack(@PathVariable Long soundtrackId){
        soundtrackRepository.deleteById(soundtrackId);
    }

    @PutMapping("/{soundtrackId}/addMusic/{musicId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Soundtrack addMusic(@PathVariable Long soundtrackId, @PathVariable Long musicId){
        Soundtrack soundtrack=soundtrackRepository.findById(soundtrackId).get();
        Music music=musicRepository.findById(musicId).get();
        soundtrack.addMusic(music);
        return soundtrackRepository.save(soundtrack);
    }
    @PutMapping("/{soundtrackId}/removeMusic/{musicId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Soundtrack removeMusic(@PathVariable Long soundtrackId, @PathVariable Long musicId){
        Soundtrack soundtrack=soundtrackRepository.findById(soundtrackId).get();
        Music music=musicRepository.findById(musicId).get();
        soundtrack.removeMusic(music);
        return soundtrackRepository.save(soundtrack);
    }

    @GetMapping("/{soundtrackId}/musics")
    public Set<Music> findAllMusicsForSoundtrack(@PathVariable Long soundtrackId){
        Soundtrack soundtrack=soundtrackRepository.findById(soundtrackId).get();
        return soundtrack.getMusics();
    }


    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Soundtrack> findSoundtracksPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return soundtrackRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
