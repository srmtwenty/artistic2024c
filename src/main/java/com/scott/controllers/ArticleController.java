package com.scott.controllers;


import com.scott.models.Article;
import com.scott.models.Person;
import com.scott.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.scott.repositories.ArticleRepository;
import com.scott.repositories.PersonRepository;
import com.scott.repositories.TagRepository;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins="http://localhost:3000")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    TagRepository tagRepository;
    @Autowired
    PersonRepository personRepository;
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Article createArticle(@RequestBody Article article){
        return articleRepository.save(article);
    }
    @GetMapping
    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }
    @GetMapping("/{id}")
    public Article findArticle(@PathVariable Long id){
        return articleRepository.findById(id).orElseThrow(()->new RuntimeException("Article has not been found!"));
    }
    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article){
        Article updateA=articleRepository.findById(id).get();
        updateA.setName(article.getName());
        updateA.setDescription(article.getDescription());
        updateA.setAddress(article.getAddress());
        //updateA.setPeople(article.getPeople());
        //updateA.setTags(article.getTags());
        updateA.setDate(article.getDate());
        return articleRepository.save(updateA);
    }
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteArticle(@PathVariable Long id){
        articleRepository.deleteById(id);
    }

    @GetMapping("/{id}/tags")
    public Set<Tag> findTagsForArticle(@PathVariable Long id){
        Article article=articleRepository.findById(id).get();
        return article.getTags();

    }
    @PutMapping("/{id}/addTag/{tagId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article addTag(@PathVariable Long id, @PathVariable Long tagId){
        Article article=articleRepository.findById(id).get();
        Tag tag=tagRepository.findById(tagId).get();
        article.addTag(tag);
        return articleRepository.save(article);
    }

    @PutMapping("/{id}/removeTag/{tagId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article removeTag(@PathVariable Long id, @PathVariable Long tagId){
        Article article=articleRepository.findById(id).get();
        Tag tag=tagRepository.findById(tagId).get();
        article.removeTag(tag);
        return articleRepository.save(article);
    }

    @GetMapping("/{id}/people")
    public Set<Person> findPeopleForArticle(@PathVariable Long id){
        Article article=articleRepository.findById(id).get();
        return article.getPeople();
    }
    @PutMapping("/{id}/addPerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article addPerson(@PathVariable Long id, @PathVariable Long personId){
        Article article=articleRepository.findById(id).get();
        Person person=personRepository.findById(personId).get();
        article.addPerson(person);
        return articleRepository.save(article);
    }
    @PutMapping("/{id}/removePerson/{personId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Article removePerson(@PathVariable Long id, @PathVariable Long personId){
        Article article=articleRepository.findById(id).get();
        Person person=personRepository.findById(personId).get();
        article.removePerson(person);
        return articleRepository.save(article);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public Page<Article> findArticlesPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return articleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }
}
