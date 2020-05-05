package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.FindAWordService;
import com.italianlearning_microservices.contrader.domain.FindAWord;
import com.italianlearning_microservices.contrader.repository.FindAWordRepository;
import com.italianlearning_microservices.contrader.service.dto.FindAWordDTO;
import com.italianlearning_microservices.contrader.service.mapper.FindAWordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing FindAWord.
 */
@Service
@Transactional
public class FindAWordServiceImpl implements FindAWordService {

    private final Logger log = LoggerFactory.getLogger(FindAWordServiceImpl.class);

    private final FindAWordRepository findAWordRepository;

    private final FindAWordMapper findAWordMapper;

    public FindAWordServiceImpl(FindAWordRepository findAWordRepository, FindAWordMapper findAWordMapper) {
        this.findAWordRepository = findAWordRepository;
        this.findAWordMapper = findAWordMapper;
    }

    /**
     * Save a findAWord.
     *
     * @param findAWordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FindAWordDTO save(FindAWordDTO findAWordDTO) {
        log.debug("Request to save FindAWord : {}", findAWordDTO);
        FindAWord findAWord = findAWordMapper.toEntity(findAWordDTO);
        findAWord = findAWordRepository.save(findAWord);
        return findAWordMapper.toDto(findAWord);
    }

    /**
     * Get all the findAWords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FindAWordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindAWords");
        return findAWordRepository.findAll(pageable)
            .map(findAWordMapper::toDto);
    }


    /**
     * Get one findAWord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FindAWordDTO> findOne(Long id) {
        log.debug("Request to get FindAWord : {}", id);
        return findAWordRepository.findById(id)
            .map(findAWordMapper::toDto);
    }

    /**
     * Delete the findAWord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FindAWord : {}", id);
        findAWordRepository.deleteById(id);
    }
}
