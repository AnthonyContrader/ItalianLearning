package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.HangmanService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.HangmanDTO;
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
 * REST controller for managing Hangman.
 */
@RestController
@RequestMapping("/api")
public class HangmanResource {

    private final Logger log = LoggerFactory.getLogger(HangmanResource.class);

    private static final String ENTITY_NAME = "hangman";

    private final HangmanService hangmanService;

    public HangmanResource(HangmanService hangmanService) {
        this.hangmanService = hangmanService;
    }

    /**
     * POST  /hangmen : Create a new hangman.
     *
     * @param hangmanDTO the hangmanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hangmanDTO, or with status 400 (Bad Request) if the hangman has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hangmen")
    @Timed
    public ResponseEntity<HangmanDTO> createHangman(@Valid @RequestBody HangmanDTO hangmanDTO) throws URISyntaxException {
        log.debug("REST request to save Hangman : {}", hangmanDTO);
        if (hangmanDTO.getId() != null) {
            throw new BadRequestAlertException("A new hangman cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HangmanDTO result = hangmanService.save(hangmanDTO);
        return ResponseEntity.created(new URI("/api/hangmen/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hangmen : Updates an existing hangman.
     *
     * @param hangmanDTO the hangmanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hangmanDTO,
     * or with status 400 (Bad Request) if the hangmanDTO is not valid,
     * or with status 500 (Internal Server Error) if the hangmanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hangmen")
    @Timed
    public ResponseEntity<HangmanDTO> updateHangman(@Valid @RequestBody HangmanDTO hangmanDTO) throws URISyntaxException {
        log.debug("REST request to update Hangman : {}", hangmanDTO);
        if (hangmanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HangmanDTO result = hangmanService.save(hangmanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hangmanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hangmen : get all the hangmen.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hangmen in body
     */
    @GetMapping("/hangmen")
    @Timed
    public ResponseEntity<List<HangmanDTO>> getAllHangmen(Pageable pageable) {
        log.debug("REST request to get a page of Hangmen");
        Page<HangmanDTO> page = hangmanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hangmen");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /hangmen/:id : get the "id" hangman.
     *
     * @param id the id of the hangmanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hangmanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hangmen/{id}")
    @Timed
    public ResponseEntity<HangmanDTO> getHangman(@PathVariable Long id) {
        log.debug("REST request to get Hangman : {}", id);
        Optional<HangmanDTO> hangmanDTO = hangmanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hangmanDTO);
    }

    /**
     * DELETE  /hangmen/:id : delete the "id" hangman.
     *
     * @param id the id of the hangmanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hangmen/{id}")
    @Timed
    public ResponseEntity<Void> deleteHangman(@PathVariable Long id) {
        log.debug("REST request to delete Hangman : {}", id);
        hangmanService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
