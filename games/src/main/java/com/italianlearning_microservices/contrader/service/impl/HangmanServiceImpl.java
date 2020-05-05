package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.HangmanService;
import com.italianlearning_microservices.contrader.domain.Hangman;
import com.italianlearning_microservices.contrader.repository.HangmanRepository;
import com.italianlearning_microservices.contrader.service.dto.HangmanDTO;
import com.italianlearning_microservices.contrader.service.mapper.HangmanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Hangman.
 */
@Service
@Transactional
public class HangmanServiceImpl implements HangmanService {

    private final Logger log = LoggerFactory.getLogger(HangmanServiceImpl.class);

    private final HangmanRepository hangmanRepository;

    private final HangmanMapper hangmanMapper;

    public HangmanServiceImpl(HangmanRepository hangmanRepository, HangmanMapper hangmanMapper) {
        this.hangmanRepository = hangmanRepository;
        this.hangmanMapper = hangmanMapper;
    }

    /**
     * Save a hangman.
     *
     * @param hangmanDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HangmanDTO save(HangmanDTO hangmanDTO) {
        log.debug("Request to save Hangman : {}", hangmanDTO);
        Hangman hangman = hangmanMapper.toEntity(hangmanDTO);
        hangman = hangmanRepository.save(hangman);
        return hangmanMapper.toDto(hangman);
    }

    /**
     * Get all the hangmen.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HangmanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Hangmen");
        return hangmanRepository.findAll(pageable)
            .map(hangmanMapper::toDto);
    }


    /**
     * Get one hangman by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HangmanDTO> findOne(Long id) {
        log.debug("Request to get Hangman : {}", id);
        return hangmanRepository.findById(id)
            .map(hangmanMapper::toDto);
    }

    /**
     * Delete the hangman by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hangman : {}", id);
        hangmanRepository.deleteById(id);
    }
}
