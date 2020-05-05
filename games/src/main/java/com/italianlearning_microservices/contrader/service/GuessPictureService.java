package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing GuessPicture.
 */
public interface GuessPictureService {

    /**
     * Save a guessPicture.
     *
     * @param guessPictureDTO the entity to save
     * @return the persisted entity
     */
    GuessPictureDTO save(GuessPictureDTO guessPictureDTO);

    /**
     * Get all the guessPictures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GuessPictureDTO> findAll(Pageable pageable);


    /**
     * Get the "id" guessPicture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GuessPictureDTO> findOne(Long id);

    /**
     * Delete the "id" guessPicture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
