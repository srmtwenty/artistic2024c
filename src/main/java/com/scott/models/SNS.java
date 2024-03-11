package com.scott.models;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class SNS implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="music_id", referencedColumnName = "id")
    private Music music;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void removePerson(Person person){
        this.person=null;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void removeMusic(Music music){
        this.music=null;
    }
}
