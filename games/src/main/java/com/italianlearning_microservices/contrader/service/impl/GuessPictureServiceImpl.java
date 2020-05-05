package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.GuessPictureService;
import com.italianlearning_microservices.contrader.domain.GuessPicture;
import com.italianlearning_microservices.contrader.repository.GuessPictureRepository;
import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;
import com.italianlearning_microservices.contrader.service.mapper.GuessPictureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing GuessPicture.
 */
@Service
@Transactional
public class GuessPictureServiceImpl implements GuessPictureService {

    private final Logger log = LoggerFactory.getLogger(GuessPictureServiceImpl.class);

    private final GuessPictureRepository guessPictureRepository;

    private final GuessPictureMapper guessPictureMapper;

    public GuessPictureServiceImpl(GuessPictureRepository guessPictureRepository, GuessPictureMapper guessPictureMapper) {
        this.guessPictureRepository = guessPictureRepository;
        this.guessPictureMapper = guessPictureMapper;
    }

    /**
     * Save a guessPicture.
     *
     * @param guessPictureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GuessPictureDTO save(GuessPictureDTO guessPictureDTO) {
        log.debug("Request to save GuessPicture : {}", guessPictureDTO);
        GuessPicture guessPicture = guessPictureMapper.toEntity(guessPictureDTO);
        guessPicture = guessPictureRepository.save(guessPicture);
        return guessPictureMapper.toDto(guessPicture);
    }

    /**
     * Get all the guessPictures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GuessPictureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GuessPictures");
        return guessPictureRepository.findAll(pageable)
            .map(guessPictureMapper::toDto);
    }


    /**
     * Get one guessPicture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GuessPictureDTO> findOne(Long id) {
        log.debug("Request to get GuessPicture : {}", id);
        return guessPictureRepository.findById(id)
            .map(guessPictureMapper::toDto);
    }

    /**
     * Delete the guessPicture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GuessPicture : {}", id);
        guessPictureRepository.deleteById(id);
    }
}
