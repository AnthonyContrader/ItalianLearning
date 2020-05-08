package com.italianlearning_microservices.contrader.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GamePlaylist.
 */
@Entity
@Table(name = "game_playlist")
public class GamePlaylist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "type_game", nullable = false)
    private String typeGame;

    @NotNull
    @Column(name = "id_game", nullable = false)
    private Integer idGame;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Playlist playlist;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeGame() {
        return typeGame;
    }

    public GamePlaylist typeGame(String typeGame) {
        this.typeGame = typeGame;
        return this;
    }

    public void setTypeGame(String typeGame) {
        this.typeGame = typeGame;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public GamePlaylist idGame(Integer idGame) {
        this.idGame = idGame;
        return this;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public GamePlaylist playlist(Playlist playlist) {
        this.playlist = playlist;
        return this;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
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
        GamePlaylist gamePlaylist = (GamePlaylist) o;
        if (gamePlaylist.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gamePlaylist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GamePlaylist{" +
            "id=" + getId() +
            ", typeGame='" + getTypeGame() + "'" +
            ", idGame=" + getIdGame() +
            "}";
    }
}
