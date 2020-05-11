package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.GamePlaylistService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.GamePlaylistDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing GamePlaylist.
 */
@RestController
@RequestMapping("/api")
public class GamePlaylistResource {

    private final Logger log = LoggerFactory.getLogger(GamePlaylistResource.class);

    private static final String ENTITY_NAME = "gamePlaylist";

    private final GamePlaylistService gamePlaylistService;

    public GamePlaylistResource(GamePlaylistService gamePlaylistService) {
        this.gamePlaylistService = gamePlaylistService;
    }

    /**
     * POST  /game-playlists : Create a new gamePlaylist.
     *
     * @param gamePlaylistDTO the gamePlaylistDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gamePlaylistDTO, or with status 400 (Bad Request) if the gamePlaylist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/game-playlists")
    @Timed
    public ResponseEntity<GamePlaylistDTO> createGamePlaylist(@Valid @RequestBody GamePlaylistDTO gamePlaylistDTO) throws URISyntaxException {
        log.debug("REST request to save GamePlaylist : {}", gamePlaylistDTO);
        if (gamePlaylistDTO.getId() != null) {
            throw new BadRequestAlertException("A new gamePlaylist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GamePlaylistDTO result = gamePlaylistService.save(gamePlaylistDTO);
        return ResponseEntity.created(new URI("/api/game-playlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /game-playlists : Updates an existing gamePlaylist.
     *
     * @param gamePlaylistDTO the gamePlaylistDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gamePlaylistDTO,
     * or with status 400 (Bad Request) if the gamePlaylistDTO is not valid,
     * or with status 500 (Internal Server Error) if the gamePlaylistDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/game-playlists")
    @Timed
    public ResponseEntity<GamePlaylistDTO> updateGamePlaylist(@Valid @RequestBody GamePlaylistDTO gamePlaylistDTO) throws URISyntaxException {
        log.debug("REST request to update GamePlaylist : {}", gamePlaylistDTO);
        if (gamePlaylistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GamePlaylistDTO result = gamePlaylistService.save(gamePlaylistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, gamePlaylistDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /game-playlists : get all the gamePlaylists.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of gamePlaylists in body
     */
    @GetMapping("/game-playlists")
    @Timed
    public ResponseEntity<List<GamePlaylistDTO>> getAllGamePlaylists(Pageable pageable) {
        log.debug("REST request to get a page of GamePlaylists");
        Page<GamePlaylistDTO> page = gamePlaylistService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/game-playlists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /game-playlists/:id : get the "id" gamePlaylist.
     *
     * @param id the id of the gamePlaylistDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gamePlaylistDTO, or with status 404 (Not Found)
     */
    @GetMapping("/game-playlists/{id}")
    @Timed
    public ResponseEntity<GamePlaylistDTO> getGamePlaylist(@PathVariable Long id) {
        log.debug("REST request to get GamePlaylist : {}", id);
        Optional<GamePlaylistDTO> gamePlaylistDTO = gamePlaylistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gamePlaylistDTO);
    }

    @GetMapping("/game-playlists/findByGame")
    @Timed
    public boolean getGamePlaylist(@RequestParam("idPlaylist") Long idPlaylist, @RequestParam("idGame") Integer idGame, @RequestParam("typeGame") String typeGame) {
        log.debug("REST request to get Playlist : {}", idPlaylist, idGame, typeGame);
        return gamePlaylistService.findByIdPlaylistAndIdGameAndTypeGame(idPlaylist, idGame, typeGame);
    }

    /**
     * DELETE  /game-playlists/:id : delete the "id" gamePlaylist.
     *
     * @param id the id of the gamePlaylistDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/game-playlists/{id}")
    @Timed
    public ResponseEntity<Void> deleteGamePlaylist(@PathVariable Long id) {
        log.debug("REST request to delete GamePlaylist : {}", id);
        gamePlaylistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @DeleteMapping("/game-playlists/deleteAllByPlaylist/{id}")
    @Timed
    public ResponseEntity<Void> deleteAllByPlaylist(@PathVariable Long id) {
        log.debug("REST request to delete GamePlaylist : {}", id);
        gamePlaylistService.deleteAllByPlaylist(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
