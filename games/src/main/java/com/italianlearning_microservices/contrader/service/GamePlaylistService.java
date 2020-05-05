package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.GamePlaylistDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GamePlaylist.
 */
public interface GamePlaylistService {

    /**
     * Save a gamePlaylist.
     *
     * @param gamePlaylistDTO the entity to save
     * @return the persisted entity
     */
    GamePlaylistDTO save(GamePlaylistDTO gamePlaylistDTO);

    /**
     * Get all the gamePlaylists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GamePlaylistDTO> findAll(Pageable pageable);


    /**
     * Get the "id" gamePlaylist.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GamePlaylistDTO> findOne(Long id);

    /**
     * Delete the "id" gamePlaylist.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
