package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.FindAWordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FindAWord.
 */
public interface FindAWordService {

    /**
     * Save a findAWord.
     *
     * @param findAWordDTO the entity to save
     * @return the persisted entity
     */
    FindAWordDTO save(FindAWordDTO findAWordDTO);

    /**
     * Get all the findAWords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FindAWordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" findAWord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FindAWordDTO> findOne(Long id);

    /**
     * Delete the "id" findAWord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
