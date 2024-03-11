package com.scott.controllers;


import com.scott.models.Artist;
import com.scott.models.Music;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.scott.repositories.ArtistRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins="http://localhost:3000")
public class ArtistController {
    @Autowired
    ArtistRepository artistRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Artist createArtist(@RequestBody Artist artist){
        return artistRepository.save(artist);
    }
    @GetMapping
    public List<Artist> findAllArtists(){
        return artistRepository.findAll();
    }
    @GetMapping("/{artistId}")
    public Artist findArtistById(@PathVariable Long artistId){
        return artistRepository.findById(artistId).orElseThrow(()->new RuntimeException("Artist has not been found!"));
    }
    @PutMapping("/{artistId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Artist updateArtist(@PathVariable Long artistId, @RequestBody Artist artist){
        Artist updateA=this.findArtistById(artistId);
        updateA.setName(artist.getName());
        updateA.setDescription(artist.getDescription());
        updateA.setMusic(artist.getMusic());
        return artistRepository.save(updateA);
    }
    @DeleteMapping("/{artistId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArtist(@PathVariable Long artistId){
        artistRepository.deleteById(artistId);
    }


    @GetMapping("/{artistId}/musics")
    public Set<Music> findMusicsForArtist(@PathVariable Long artistId){
        Artist artist=this.findArtistById(artistId);
        Set<Music> musics=artist.getMusic();
        return musics;
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Artist> findArtistsPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return artistRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
