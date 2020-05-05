package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.OrganizeSentenceService;
import com.italianlearning_microservices.contrader.domain.OrganizeSentence;
import com.italianlearning_microservices.contrader.repository.OrganizeSentenceRepository;
import com.italianlearning_microservices.contrader.service.dto.OrganizeSentenceDTO;
import com.italianlearning_microservices.contrader.service.mapper.OrganizeSentenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing OrganizeSentence.
 */
@Service
@Transactional
public class OrganizeSentenceServiceImpl implements OrganizeSentenceService {

    private final Logger log = LoggerFactory.getLogger(OrganizeSentenceServiceImpl.class);

    private final OrganizeSentenceRepository organizeSentenceRepository;

    private final OrganizeSentenceMapper organizeSentenceMapper;

    public OrganizeSentenceServiceImpl(OrganizeSentenceRepository organizeSentenceRepository, OrganizeSentenceMapper organizeSentenceMapper) {
        this.organizeSentenceRepository = organizeSentenceRepository;
        this.organizeSentenceMapper = organizeSentenceMapper;
    }

    /**
     * Save a organizeSentence.
     *
     * @param organizeSentenceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrganizeSentenceDTO save(OrganizeSentenceDTO organizeSentenceDTO) {
        log.debug("Request to save OrganizeSentence : {}", organizeSentenceDTO);
        OrganizeSentence organizeSentence = organizeSentenceMapper.toEntity(organizeSentenceDTO);
        organizeSentence = organizeSentenceRepository.save(organizeSentence);
        return organizeSentenceMapper.toDto(organizeSentence);
    }

    /**
     * Get all the organizeSentences.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrganizeSentenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrganizeSentences");
        return organizeSentenceRepository.findAll(pageable)
            .map(organizeSentenceMapper::toDto);
    }


    /**
     * Get one organizeSentence by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizeSentenceDTO> findOne(Long id) {
        log.debug("Request to get OrganizeSentence : {}", id);
        return organizeSentenceRepository.findById(id)
            .map(organizeSentenceMapper::toDto);
    }

    /**
     * Delete the organizeSentence by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizeSentence : {}", id);
        organizeSentenceRepository.deleteById(id);
    }
}
