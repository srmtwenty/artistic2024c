package com.scott.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Routine implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated
    private Genre genre;

    @Enumerated
    private Type type;

    @Column(name="description", columnDefinition="text")
    private String description;

    private Long rank;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="music_routines",
            joinColumns=@JoinColumn(name="routine_id"),
            inverseJoinColumns=@JoinColumn(name="music_id")
    )
    private Set<Music> musics=new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="competition_id", referencedColumnName = "id")
    private Competition competition;

    @ManyToMany
    @JoinTable(
            name="swimmers_routines",
            joinColumns=@JoinColumn(name="routine_id"),
            inverseJoinColumns=@JoinColumn(name="swimmer_id")
    )
    private Set<Person> swimmers=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="coaches_routines",
            joinColumns=@JoinColumn(name="routinec_id"),
            inverseJoinColumns=@JoinColumn(name="coach_id")
    )
    private Set<Person> coaches=new HashSet<>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="nationalTeam_id", referencedColumnName="id")
    private NationalTeam nationalTeam;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Music> getMusics() {
        return musics;
    }

    public void setMusics(Set<Music> musics) {
        this.musics = musics;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Set<Person> getSwimmers() {
        return swimmers;
    }

    public void setSwimmers(Set<Person> swimmers) {
        this.swimmers = swimmers;
    }

    public Set<Person> getCoaches() {
        return coaches;
    }

    public void setCoaches(Set<Person> coaches) {
        this.coaches = coaches;
    }

    public NationalTeam getNationalTeam() {
        return nationalTeam;
    }

    public void setNationalTeam(NationalTeam nationalTeam) {
        this.nationalTeam = nationalTeam;
    }

    //add music to routine
    public void addMusic(Music music){
        musics.add(music);
    }

    //remove music from routine
    public void removeMusic(Music music){
        musics.remove(music);
    }

    public void addSwimmer(Person swimmer){
        swimmers.add(swimmer);
    }
    public void removeSwimmer(Person swimmer){
        swimmers.remove(swimmer);
    }

    public void addCoach(Person person){
        coaches.add(person);
    }
    public void removeCoach(Person person){
        coaches.remove(person);
    }

    public void assignNationalTeam(NationalTeam nationalTeam){
        this.nationalTeam=nationalTeam;
    }
    public void removeNationalTeam(NationalTeam nationalTeam){
        this.nationalTeam=null;
    }

    public void removeCompetition(Competition competition){
        this.competition=null;
    }
}
