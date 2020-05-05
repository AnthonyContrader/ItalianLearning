package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Playlist.
 */
@Entity
@Table(name = "playlist")
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "playlist")
    private Set<GamePlaylist> gamePlaylists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Playlist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Playlist description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GamePlaylist> getGamePlaylists() {
        return gamePlaylists;
    }

    public Playlist gamePlaylists(Set<GamePlaylist> gamePlaylists) {
        this.gamePlaylists = gamePlaylists;
        return this;
    }

    public Playlist addGamePlaylist(GamePlaylist gamePlaylist) {
        this.gamePlaylists.add(gamePlaylist);
        gamePlaylist.setPlaylist(this);
        return this;
    }

    public Playlist removeGamePlaylist(GamePlaylist gamePlaylist) {
        this.gamePlaylists.remove(gamePlaylist);
        gamePlaylist.setPlaylist(null);
        return this;
    }

    public void setGamePlaylists(Set<GamePlaylist> gamePlaylists) {
        this.gamePlaylists = gamePlaylists;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Playlist playlist = (Playlist) o;
        if (playlist.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), playlist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Playlist{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
