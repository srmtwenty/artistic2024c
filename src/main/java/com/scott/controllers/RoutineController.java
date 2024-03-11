package com.scott.controllers;

import com.scott.models.*;
import com.scott.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routines")
@CrossOrigin(origins="http://localhost:3000")
public class RoutineController {
    @Autowired
    RoutineRepository routineRepository;
    @Autowired
    MusicRepository musicRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    NationalTeamRepository nationalTeamRepository;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine createRoutine(@RequestBody Routine routine){
        return routineRepository.save(routine);
    }
    @GetMapping
    public List<Routine> findAllRoutines(){
        return routineRepository.findAll();
    }
    @GetMapping("/{routineId}")
    public Routine findRoutineById(@PathVariable Long routineId){
        return routineRepository.findById(routineId).orElseThrow(()->new RuntimeException("Routine has not been found!"));
    }
    @PutMapping("/{routineId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine updateRoutine(@PathVariable Long routineId, @RequestBody Routine routine){
        Routine updateR=this.findRoutineById(routineId);
        updateR.setName(routine.getName());
        updateR.setGenre(routine.getGenre());
        updateR.setType(routine.getType());
        updateR.setDescription(routine.getDescription());
        updateR.setRank(routine.getRank());
        updateR.setDate(routine.getDate());
        //updateR.setMusics(routine.getMusics());
        //updateR.setCompetition(routine.getCompetition());
        //updateR.setSwimmers(routine.getSwimmers());
        //updateR.setCoaches(routine.getCoaches());
        //updateR.setNationalTeam(routine.getNationalTeam());
        return routineRepository.save(updateR);
    }
    @DeleteMapping("/{routineId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRoutine(@PathVariable Long routineId){
        routineRepository.deleteById(routineId);
    }
    @PutMapping("/{routineId}/addMusic/{musicId}")
    public Routine addMusic(@PathVariable Long routineId, @PathVariable Long musicId){
        Routine routine=routineRepository.findById(routineId).get();
        Music music=musicRepository.findById(musicId).get();
        routine.addMusic(music);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/removeMusic/{musicId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine removeMusic(@PathVariable Long routineId, @PathVariable Long musicId){
        Routine routine=routineRepository.findById(routineId).get();
        Music music=musicRepository.findById(musicId).get();
        routine.removeMusic(music);
        return routineRepository.save(routine);
    }

    @PutMapping("/{routineId}/addSwimmer/{swimmerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine addSwimmer(@PathVariable Long routineId, @PathVariable Long swimmerId){
        Routine routine=routineRepository.findById(routineId).get();
        Person swimmer=personRepository.findById(swimmerId).get();
        routine.addSwimmer(swimmer);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/removeSwimmer/{swimmerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine removePerson(@PathVariable Long routineId, @PathVariable Long swimmerId){
        Routine routine=routineRepository.findById(routineId).get();
        Person swimmer=personRepository.findById(swimmerId).get();
        routine.removeSwimmer(swimmer);
        return routineRepository.save(routine);
    }

    @PutMapping("/{routineId}/addCoach/{coachId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine addCoach(@PathVariable Long routineId, @PathVariable Long coachId){
        Routine routine=routineRepository.findById(routineId).get();
        Person coach=personRepository.findById(coachId).get();
        routine.addCoach(coach);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/removeCoach/{coachId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine removeCoach(@PathVariable Long routineId, @PathVariable Long coachId){
        Routine routine=routineRepository.findById(routineId).get();
        Person coach=personRepository.findById(coachId).get();
        routine.removeCoach(coach);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/assignCompetition/{competitionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine assignCompetition(@PathVariable Long routineId, @PathVariable Long competitionId){
        Routine routine=routineRepository.findById(routineId).get();
        Competition competition=competitionRepository.findById(competitionId).get();
        routine.setCompetition(competition);
        return routineRepository.save(routine);
    }


    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Routine> findRoutinesPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return routineRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }

    @PutMapping("/{routineId}/assignNationalTeam/{nationalTeamId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine assignNationalTeam(@PathVariable Long routineId, @PathVariable Long nationalTeamId){
        Routine routine=routineRepository.findById(routineId).get();
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        routine.assignNationalTeam(nationalTeam);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/removeNationalTeam/{nationalTeamId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine removeNationalTeam(@PathVariable Long routineId, @PathVariable Long nationalTeamId){
        Routine routine=routineRepository.findById(routineId).get();
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        routine.removeNationalTeam(nationalTeam);
        return routineRepository.save(routine);
    }
    @PutMapping("/{routineId}/removeCompetition/{competitionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Routine removeCompetition(@PathVariable Long routineId, @PathVariable Long competitionId){
        Routine routine=routineRepository.findById(routineId).get();
        Competition competition=competitionRepository.findById(competitionId).get();
        routine.removeCompetition(competition);
        return routineRepository.save(routine);
    }
}
