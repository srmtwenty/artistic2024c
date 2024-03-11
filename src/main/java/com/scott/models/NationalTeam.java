package com.scott.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NationalTeam implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="description", columnDefinition="text")
    private String description;

    //@OneToOne(fetch=FetchType.LAZY)
    //@MapsId
    //@OneToOne
    //@OneToOne(cascade=CascadeType.ALL)
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="nation_id", referencedColumnName ="id")
    //@JoinColumn(name="nation_id")
    private Nation nation;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="people_teams",
            joinColumns = @JoinColumn(name="nationalTeam_id"),
            inverseJoinColumns = @JoinColumn(name="person_id")
    )
    private Set<Person> members=new HashSet<>();

    @ManyToMany(mappedBy="participatedTeams")
    @JsonIgnore
    private Set<Competition> competitions;

    @OneToMany(mappedBy="nationalTeam")
    @JsonIgnore
    private Set<Routine> routines=new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Set<Person> getMembers() {
        return members;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    //add person
    public void addPerson(Person person){
        members.add(person);
    }
    //remove Person
    public void removePerson(Person person){
        members.remove(person);
    }

    public void removeNation(Nation nation){
        this.nation=null;
    }
}
