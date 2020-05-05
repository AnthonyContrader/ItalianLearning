package com.italianlearning_microservices.contrader.service.impl;

import com.italianlearning_microservices.contrader.service.GamePlaylistService;
import com.italianlearning_microservices.contrader.domain.GamePlaylist;
import com.italianlearning_microservices.contrader.repository.GamePlaylistRepository;
import com.italianlearning_microservices.contrader.service.dto.GamePlaylistDTO;
import com.italianlearning_microservices.contrader.service.mapper.GamePlaylistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing GamePlaylist.
 */
@Service
@Transactional
public class GamePlaylistServiceImpl implements GamePlaylistService {

    private final Logger log = LoggerFactory.getLogger(GamePlaylistServiceImpl.class);

    private final GamePlaylistRepository gamePlaylistRepository;

    private final GamePlaylistMapper gamePlaylistMapper;

    public GamePlaylistServiceImpl(GamePlaylistRepository gamePlaylistRepository, GamePlaylistMapper gamePlaylistMapper) {
        this.gamePlaylistRepository = gamePlaylistRepository;
        this.gamePlaylistMapper = gamePlaylistMapper;
    }

    /**
     * Save a gamePlaylist.
     *
     * @param gamePlaylistDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GamePlaylistDTO save(GamePlaylistDTO gamePlaylistDTO) {
        log.debug("Request to save GamePlaylist : {}", gamePlaylistDTO);
        GamePlaylist gamePlaylist = gamePlaylistMapper.toEntity(gamePlaylistDTO);
        gamePlaylist = gamePlaylistRepository.save(gamePlaylist);
        return gamePlaylistMapper.toDto(gamePlaylist);
    }

    /**
     * Get all the gamePlaylists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GamePlaylistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GamePlaylists");
        return gamePlaylistRepository.findAll(pageable)
            .map(gamePlaylistMapper::toDto);
    }


    /**
     * Get one gamePlaylist by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GamePlaylistDTO> findOne(Long id) {
        log.debug("Request to get GamePlaylist : {}", id);
        return gamePlaylistRepository.findById(id)
            .map(gamePlaylistMapper::toDto);
    }

    /**
     * Delete the gamePlaylist by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GamePlaylist : {}", id);
        gamePlaylistRepository.deleteById(id);
    }
}
