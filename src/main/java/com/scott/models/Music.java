package com.scott.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Music implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="description", columnDefinition="text")
    private String description;



    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="soundtrack_id", referencedColumnName = "id")

    private Soundtrack soundtrack;

    @ManyToMany(mappedBy="musics")
    @JsonIgnore
    private Set<Routine> routines;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="artists_musics",
            joinColumns = @JoinColumn(name="music_id"),
            inverseJoinColumns = @JoinColumn(name="artist_id")
    )
    private Set<Artist> artists=new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="composers_musics",
            joinColumns = @JoinColumn(name="musicC_id"),
            inverseJoinColumns = @JoinColumn(name="composer_id")
    )
    private Set<Artist> composers=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="music")
    private Set<SNS> musicLinks=new HashSet<>();

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


    public Soundtrack getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Artist> getComposers() {
        return composers;
    }

    public void setComposers(Set<Artist> composers) {
        this.composers = composers;
    }

    //add composers
    public void addComposer(Artist composer){
        composers.add(composer);
    }

    //add artists
    public void addArtist(Artist artist){
        artists.add(artist);
    }
    public void removeComposer(Artist composer){
        composers.remove(composer);
    }

    //add artists
    public void removeArtist(Artist artist){
        artists.remove(artist);
    }

    public void removeSoundtrack(Soundtrack soundtrack){
        this.soundtrack=null;
    }

    public Set<SNS> getMusicLinks() {
        return musicLinks;
    }

    public void setMusicLinks(Set<SNS> musicLinks) {
        this.musicLinks = musicLinks;
    }

    public void addMusicLink(SNS sns){
        musicLinks.add(sns);
    }
    public void removeMusicLink(SNS sns){
        musicLinks.remove(sns);
    }
}
