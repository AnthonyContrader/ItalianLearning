package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.HangmanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Hangman.
 */
public interface HangmanService {

    /**
     * Save a hangman.
     *
     * @param hangmanDTO the entity to save
     * @return the persisted entity
     */
    HangmanDTO save(HangmanDTO hangmanDTO);

    /**
     * Get all the hangmen.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HangmanDTO> findAll(Pageable pageable);


    /**
     * Get the "id" hangman.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HangmanDTO> findOne(Long id);

    /**
     * Delete the "id" hangman.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
