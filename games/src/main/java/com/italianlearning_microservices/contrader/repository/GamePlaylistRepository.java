package com.italianlearning_microservices.contrader.repository;

import com.italianlearning_microservices.contrader.domain.GamePlaylist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GamePlaylist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GamePlaylistRepository extends JpaRepository<GamePlaylist, Long> {

}
