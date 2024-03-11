package com.scott.controllers;

import com.scott.models.Person;
import com.scott.models.Tag;
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
@RequestMapping("/tags")
@CrossOrigin(origins="http://localhost:3000")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Tag createTag(@RequestBody Tag tag){
        return tagRepository.save(tag);
    }
    @GetMapping
    public List<Tag> findAllTags(){
        return tagRepository.findAll();
    }
    @GetMapping("/{tagId}")
    public Tag findTagById(@PathVariable Long tagId){
        return tagRepository.findById(tagId).orElseThrow(()->new RuntimeException("Tag has not been found!"));
    }
    @PutMapping("/{tagId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Tag updateTag(@PathVariable Long tagId, @RequestBody Tag tag){
        Tag updateT=this.findTagById(tagId);
        updateT.setName(tag.getName());
        //updateT.setPeople(tag.getPeople());
        //updateT.setArticles(tag.getArticles());
        return tagRepository.save(updateT);
    }
    @DeleteMapping("/{tagId}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTag(@PathVariable Long tagId){
        tagRepository.deleteById(tagId);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Tag> findTagsPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return tagRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }

    @GetMapping("/{tagId}/people")
    public Set<Person> findPeopleForTag(@PathVariable Long tagId){
        Tag tag=tagRepository.findById(tagId).get();
        Set<Person> people=tag.getPeople();
        return people;
    }
}
