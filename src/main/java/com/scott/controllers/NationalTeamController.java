package com.scott.controllers;

import com.scott.models.Nation;
import com.scott.models.NationalTeam;
import com.scott.models.Person;
import com.scott.repositories.NationRepository;
import com.scott.repositories.NationalTeamRepository;
import com.scott.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nationalTeams")
@CrossOrigin(origins="http://localhost:3000")
public class NationalTeamController {
    @Autowired
    NationalTeamRepository nationalTeamRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    NationRepository nationRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam createNationalTeam(@RequestBody NationalTeam nationalTeam){
        return nationalTeamRepository.save(nationalTeam);
    }
    @GetMapping
    public List<NationalTeam> findAllNationalTeams(){
        return nationalTeamRepository.findAll();
    }
    @GetMapping("/{nationalTeamId}")
    public NationalTeam findNationalTeamById(@PathVariable Long nationalTeamId){
        return nationalTeamRepository.findById(nationalTeamId).orElseThrow(()->new RuntimeException("National Team has not been found!"));
    }
    @PutMapping("/{nationalTeamId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam updateNationalTeam(@PathVariable Long nationalTeamId, @RequestBody NationalTeam nationalTeam){
        NationalTeam updateN=this.findNationalTeamById(nationalTeamId);
        updateN.setName(nationalTeam.getName());
        updateN.setNation(nationalTeam.getNation());
        updateN.setDescription(nationalTeam.getDescription());
        //updateN.setMembers(nationalTeam.getMembers());
        //updateN.setCompetitions(nationalTeam.getCompetitions());
        //updateN.setNation(nationalTeam.getNation());
        //updateN.setRoutines(nationalTeam.getRoutines());
        return nationalTeamRepository.save(updateN);
    }
    @DeleteMapping("/{nationalTeamId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteNationalTeam(@PathVariable Long nationalTeamId){
        nationalTeamRepository.deleteById(nationalTeamId);
    }

    @PutMapping("/{nationalTeamId}/addPerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam addPerson(@PathVariable Long nationalTeamId, @PathVariable Long personId){
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        Person person=personRepository.findById(personId).get();
        nationalTeam.addPerson(person);
        return nationalTeamRepository.save(nationalTeam);
    }
    @PutMapping("/{nationalTeamId}/removePerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam removePerson(@PathVariable Long nationalTeamId, @PathVariable Long personId){
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        Person person=personRepository.findById(personId).get();
        nationalTeam.removePerson(person);
        return nationalTeamRepository.save(nationalTeam);
    }

    @PutMapping("/{nationalTeamId}/assignNation/{nationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam assignNation(@PathVariable Long nationalTeamId, @PathVariable Long nationId){
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        Nation nation=nationRepository.findById(nationId).get();
        nationalTeam.setNation(nation);
        return nationalTeamRepository.save(nationalTeam);
    }
    @PutMapping("/{nationalTeamId}/removeNation/{nationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public NationalTeam removeNation(@PathVariable Long nationalTeamId, @PathVariable Long nationId){
        NationalTeam nationalTeam=nationalTeamRepository.findById(nationalTeamId).get();
        Nation nation=nationRepository.findById(nationId).get();
        nationalTeam.removeNation(nation);
        return nationalTeamRepository.save(nationalTeam);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<NationalTeam> findNationalTeamsPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return nationalTeamRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
