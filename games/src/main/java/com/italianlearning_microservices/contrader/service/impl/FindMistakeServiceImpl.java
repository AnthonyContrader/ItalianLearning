package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.FindMistakeService;
import com.italianlearning_microservices.contrader.domain.FindMistake;
import com.italianlearning_microservices.contrader.repository.FindMistakeRepository;
import com.italianlearning_microservices.contrader.service.dto.FindMistakeDTO;
import com.italianlearning_microservices.contrader.service.mapper.FindMistakeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing FindMistake.
 */
@Service
@Transactional
public class FindMistakeServiceImpl implements FindMistakeService {

    private final Logger log = LoggerFactory.getLogger(FindMistakeServiceImpl.class);

    private final FindMistakeRepository findMistakeRepository;

    private final FindMistakeMapper findMistakeMapper;

    public FindMistakeServiceImpl(FindMistakeRepository findMistakeRepository, FindMistakeMapper findMistakeMapper) {
        this.findMistakeRepository = findMistakeRepository;
        this.findMistakeMapper = findMistakeMapper;
    }

    /**
     * Save a findMistake.
     *
     * @param findMistakeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FindMistakeDTO save(FindMistakeDTO findMistakeDTO) {
        log.debug("Request to save FindMistake : {}", findMistakeDTO);
        FindMistake findMistake = findMistakeMapper.toEntity(findMistakeDTO);
        findMistake = findMistakeRepository.save(findMistake);
        return findMistakeMapper.toDto(findMistake);
    }

    /**
     * Get all the findMistakes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FindMistakeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindMistakes");
        return findMistakeRepository.findAll(pageable)
            .map(findMistakeMapper::toDto);
    }


    /**
     * Get one findMistake by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FindMistakeDTO> findOne(Long id) {
        log.debug("Request to get FindMistake : {}", id);
        return findMistakeRepository.findById(id)
            .map(findMistakeMapper::toDto);
    }

    /**
     * Delete the findMistake by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FindMistake : {}", id);
        findMistakeRepository.deleteById(id);
    }
}
