package com.scott.controllers;

import com.scott.models.SNS;
import com.scott.repositories.SNSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/snss")
@CrossOrigin(origins="http://localhost:3000")
public class SNSController {
    @Autowired
    SNSRepository snsRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public SNS createSNS(@RequestBody SNS sns){
        return snsRepository.save(sns);
    }
    @GetMapping
    public List<SNS> getSNSs(){
        return snsRepository.findAll();
    }
    @GetMapping("/{id}")
    public SNS findSNS(@PathVariable Long id){
        return snsRepository.findById(id).orElseThrow(()->new RuntimeException());
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public SNS updateSNS(@PathVariable Long id, @RequestBody SNS sns){
        SNS updateS=this.findSNS(id);
        updateS.setName(sns.getName());
        //updateS.setPerson(sns.getPerson());
        //updateS.setMusic(sns.getMusic());
        return snsRepository.save(updateS);
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSNS(@PathVariable Long id){
        snsRepository.deleteById(id);
    }
}
