package com.italianlearning_microservices.contrader.service;

import com.italianlearning_microservices.contrader.service.dto.OrganizeSentenceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrganizeSentence.
 */
public interface OrganizeSentenceService {

    /**
     * Save a organizeSentence.
     *
     * @param organizeSentenceDTO the entity to save
     * @return the persisted entity
     */
    OrganizeSentenceDTO save(OrganizeSentenceDTO organizeSentenceDTO);

    /**
     * Get all the organizeSentences.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrganizeSentenceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" organizeSentence.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrganizeSentenceDTO> findOne(Long id);

    /**
     * Delete the "id" organizeSentence.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
