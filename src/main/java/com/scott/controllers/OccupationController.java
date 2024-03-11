package com.scott.controllers;

import com.scott.models.Occupation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.scott.models.Occupation;
import com.scott.repositories.OccupationRepository;

import java.util.List;

@RestController
@RequestMapping("/occupations")
@CrossOrigin(origins="*", maxAge=3600)

public class OccupationController {
    @Autowired
    OccupationRepository occupationRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Occupation createOccupation(@RequestBody Occupation occupation){
        return occupationRepository.save(occupation);
    }
    @GetMapping
    public List<Occupation> findAllOccupations(){
        return occupationRepository.findAll();
    }
    @GetMapping("/{id}")
    public Occupation findOccupationById(@PathVariable Long id){
        return occupationRepository.findById(id).orElseThrow(()->new RuntimeException());
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Occupation updateOccupation(@PathVariable Long id, @RequestBody Occupation occupation){
        Occupation updateO=this.findOccupationById(id);
        updateO.setName(occupation.getName());
        //updateR.setPersons(role.getPersons());
        return occupationRepository.save(updateO);
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteOccupation(@PathVariable Long id){
        occupationRepository.deleteById(id);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Occupation> findOccupationsPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return occupationRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
