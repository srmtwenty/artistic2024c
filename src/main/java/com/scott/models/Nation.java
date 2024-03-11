package com.scott.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="nationality")
    @JsonIgnore
    private Set<Person> people=new HashSet<>();

    @OneToMany(mappedBy="nation")
    @JsonIgnore
    private Set<Competition> competitions=new HashSet<>();


    @OneToMany(mappedBy="nation")
    @JsonIgnore
    private Set<NationalTeam> nationalTeams=new HashSet<>();

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

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<Competition> competitions) {
        this.competitions = competitions;
    }

    public Set<NationalTeam> getNationalTeams() {
        return nationalTeams;
    }

    public void setNationalTeams(Set<NationalTeam> nationalTeams) {
        this.nationalTeams = nationalTeams;
    }

}
