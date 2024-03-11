package com.scott.controllers;

import com.scott.models.Broadcast;
import com.scott.models.Person;
import com.scott.models.Tag;
import com.scott.repositories.BroadcastRepository;
import com.scott.repositories.PersonRepository;
import com.scott.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/broadcasts")
@CrossOrigin(origins="http://localhost:3000")
public class BroadcastController {
    @Autowired
    BroadcastRepository broadcastRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PersonRepository personRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast createBroadcast(@RequestBody Broadcast broadcast){
        return broadcastRepository.save(broadcast);
    }
    @GetMapping
    public List<Broadcast> findAllBroadcast(){
        return broadcastRepository.findAll();
    }
    @GetMapping("/{id}")
    public Broadcast findBroadcastById(@PathVariable Long id){
        return broadcastRepository.findById(id).orElseThrow(()->new RuntimeException("Broadcast has not been found!"));
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast updateBroadcast(@PathVariable Long id, @RequestBody Broadcast broadcast){
        Broadcast updateB=this.findBroadcastById(id);
        updateB.setName(broadcast.getName());
        updateB.setDate(broadcast.getDate());
        updateB.setDescription(broadcast.getDescription());
        //updateB.setTags(broadcast.getTags());
        //updateB.setUrl(broadcast.getUrl());
        //updateB.setPeople(broadcast.getPeople());
        return broadcastRepository.save(updateB);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBroadcast(@PathVariable Long id){
        broadcastRepository.deleteById(id);
    }

    @GetMapping("/{id}/tags")
    public Set<Tag> findTagsForBroadcast(@PathVariable Long id){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        return broadcast.getTags();

    }
    @PutMapping("/{id}/addTag/{tagId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast addTag(@PathVariable Long id, @PathVariable Long tagId){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        Tag tag=tagRepository.findById(tagId).get();
        broadcast.addTag(tag);
        return broadcastRepository.save(broadcast);
    }

    @PutMapping("/{id}/removeTag/{tagId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast removeTag(@PathVariable Long id, @PathVariable Long tagId){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        Tag tag=tagRepository.findById(tagId).get();
        broadcast.removeTag(tag);
        return broadcastRepository.save(broadcast);
    }

    @GetMapping("/{id}/people")
    public Set<Person> findPeopleForArticle(@PathVariable Long id){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        return broadcast.getPeople();
    }
    @PutMapping("/{id}/addPerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast addPerson(@PathVariable Long id, @PathVariable Long personId){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        Person person=personRepository.findById(personId).get();
        broadcast.addPerson(person);
        return broadcastRepository.save(broadcast);
    }
    @PutMapping("/{id}/removePerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Broadcast removePerson(@PathVariable Long id, @PathVariable Long personId){
        Broadcast broadcast=broadcastRepository.findById(id).get();
        Person person=personRepository.findById(personId).get();
        broadcast.removePerson(person);
        return broadcastRepository.save(broadcast);
    }
    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Broadcast> findArticlesPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return broadcastRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }

}
