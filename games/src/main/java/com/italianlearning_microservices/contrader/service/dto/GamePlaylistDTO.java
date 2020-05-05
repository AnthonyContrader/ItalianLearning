package com.italianlearning_microservices.contrader.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GamePlaylist entity.
 */
public class GamePlaylistDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer idGame;

    @NotNull
    private String typeGame;

    private Long playlistId;

    private String playlistName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(String typeGame) {
        this.typeGame = typeGame;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GamePlaylistDTO gamePlaylistDTO = (GamePlaylistDTO) o;
        if (gamePlaylistDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gamePlaylistDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GamePlaylistDTO{" +
            "id=" + getId() +
            ", idGame=" + getIdGame() +
            ", typeGame='" + getTypeGame() + "'" +
            ", playlist=" + getPlaylistId() +
            ", playlist='" + getPlaylistName() + "'" +
            "}";
    }
}
