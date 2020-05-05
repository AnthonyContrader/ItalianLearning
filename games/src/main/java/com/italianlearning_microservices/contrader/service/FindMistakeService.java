package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.FindMistakeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FindMistake.
 */
public interface FindMistakeService {

    /**
     * Save a findMistake.
     *
     * @param findMistakeDTO the entity to save
     * @return the persisted entity
     */
    FindMistakeDTO save(FindMistakeDTO findMistakeDTO);

    /**
     * Get all the findMistakes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FindMistakeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" findMistake.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FindMistakeDTO> findOne(Long id);

    /**
     * Delete the "id" findMistake.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
