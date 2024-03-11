package com.scott.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birth;
    @Column(name="description", columnDefinition="text")
    private String description;

    @Enumerated
    private Gender gender;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="nation_id", referencedColumnName = "id")
    private Nation nationality;

    @JsonIgnore
    @OneToMany(mappedBy="person")
    private Set<SNS> sns_addresses=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="people_occupations",
            joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns=@JoinColumn(name="occupations_id")
    )
    private Set<Occupation> occupations=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="people_tags",
            joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns=@JoinColumn(name="tag_id")
    )
    private Set<Tag> tags=new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy="members")
    private Set<NationalTeam> nationalTeams;

    @JsonIgnore
    @ManyToMany(mappedBy="swimmers")
    private Set<Routine> routines;

    @JsonIgnore
    @ManyToMany(mappedBy="coaches")
    private Set<Routine> routinesForCoaches;

    /*
    @ManyToMany
    @JoinTable(
            name="people_articles",
            joinColumns = @JoinColumn(name="person_id"),
            inverseJoinColumns = @JoinColumn(name="article_id")
    )
    private Set<Article> articles=new HashSet<>();


     */
    @JsonIgnore
    @ManyToMany(mappedBy="people")
    private Set<Article> articles;

    @JsonIgnore
    @ManyToMany(mappedBy="people")
    private Set<Broadcast> broadcasts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Nation getNationality() {
        return nationality;
    }

    public void setNationality(Nation nationality) {
        this.nationality = nationality;
    }

    public Set<Occupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(Set<Occupation> occupations) {
        this.occupations = occupations;
    }

    public Set<NationalTeam> getNationalTeams() {
        return nationalTeams;
    }

    public void setNationalTeams(Set<NationalTeam> nationalTeams) {
        this.nationalTeams = nationalTeams;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    public Set<Routine> getRoutinesForCoaches() {
        return routinesForCoaches;
    }

    public void setRoutinesForCoaches(Set<Routine> routinesForCoaches) {
        this.routinesForCoaches = routinesForCoaches;
    }

    public Set<SNS> getSns_addresses() {
        return sns_addresses;
    }

    public void setSns_addresses(Set<SNS> sns_addresses) {
        this.sns_addresses = sns_addresses;
    }

    public Set<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(Set<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    //add occupation to person
    public void addOccupation(Occupation occupation){
        occupations.add(occupation);
    }
    public void removeOccupation(Occupation occupation){
        occupations.remove(occupation);
    }



    public void addTag(Tag tag){
        tags.add(tag);
    }
    public void removeTag(Tag tag){
        tags.remove(tag);
    }

    public void addSNS(SNS sns){
        sns_addresses.add(sns);
    }
    public void removeSNS(SNS sns){
        sns_addresses.remove(sns);
    }

    public void removeNationality(Nation nationality){
        this.nationality=null;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
    public void addArticle(Article article){
        articles.add(article);
    }
    public void removeArticle(Article article){
        articles.remove(article);
    }
}
