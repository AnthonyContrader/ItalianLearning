package com.italianlearning_microservices.contrader.repository;

import java.util.Optional;

import com.italianlearning_microservices.contrader.domain.GamePlaylist;
import com.italianlearning_microservices.contrader.domain.Playlist;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GamePlaylist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GamePlaylistRepository extends JpaRepository<GamePlaylist, Long> {

    public Optional<GamePlaylist> findByPlaylistAndIdGameAndTypeGame(Playlist playlist, Integer idGame, String typeGame);
    public void deleteAllByPlaylist(Playlist playlist);

}
