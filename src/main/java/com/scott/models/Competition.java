package com.scott.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Competition implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="description", columnDefinition="text")
    private String description;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="competitions_teams",
            joinColumns=@JoinColumn(name="competition_id"),
            inverseJoinColumns=@JoinColumn(name="national_id")
    )
    private Set<NationalTeam> participatedTeams=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="competition")
    private Set<Routine> routines=new HashSet<>();

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String location;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="nation_id", referencedColumnName = "id")
    private Nation nation;

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

    public Set<NationalTeam> getParticipatedTeams() {
        return participatedTeams;
    }

    public void setParticipatedTeams(Set<NationalTeam> participatedTeams) {
        this.participatedTeams = participatedTeams;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Nation getNation() {
        return nation;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    //add national team to competition
    public void addNationalTeam(NationalTeam nationalTeam){
        participatedTeams.add(nationalTeam);
    }

    //add routine to competition
    public void addRoutine(Routine routine){
        routines.add(routine);
    }

    public void removeNationalTeam(NationalTeam nationalTeam){
        participatedTeams.remove(nationalTeam);
    }

    //add routine to competition
    public void removeRoutine(Routine routine){
        routines.remove(routine);
    }

    public void removeNation(Nation nation){
        this.nation=null;
    }


}
