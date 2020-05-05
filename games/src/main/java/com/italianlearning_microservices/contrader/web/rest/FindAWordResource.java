package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.FindAWordService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.FindAWordDTO;
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
 * REST controller for managing FindAWord.
 */
@RestController
@RequestMapping("/api")
public class FindAWordResource {

    private final Logger log = LoggerFactory.getLogger(FindAWordResource.class);

    private static final String ENTITY_NAME = "findAWord";

    private final FindAWordService findAWordService;

    public FindAWordResource(FindAWordService findAWordService) {
        this.findAWordService = findAWordService;
    }

    /**
     * POST  /find-a-words : Create a new findAWord.
     *
     * @param findAWordDTO the findAWordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new findAWordDTO, or with status 400 (Bad Request) if the findAWord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/find-a-words")
    @Timed
    public ResponseEntity<FindAWordDTO> createFindAWord(@Valid @RequestBody FindAWordDTO findAWordDTO) throws URISyntaxException {
        log.debug("REST request to save FindAWord : {}", findAWordDTO);
        if (findAWordDTO.getId() != null) {
            throw new BadRequestAlertException("A new findAWord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindAWordDTO result = findAWordService.save(findAWordDTO);
        return ResponseEntity.created(new URI("/api/find-a-words/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /find-a-words : Updates an existing findAWord.
     *
     * @param findAWordDTO the findAWordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated findAWordDTO,
     * or with status 400 (Bad Request) if the findAWordDTO is not valid,
     * or with status 500 (Internal Server Error) if the findAWordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/find-a-words")
    @Timed
    public ResponseEntity<FindAWordDTO> updateFindAWord(@Valid @RequestBody FindAWordDTO findAWordDTO) throws URISyntaxException {
        log.debug("REST request to update FindAWord : {}", findAWordDTO);
        if (findAWordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindAWordDTO result = findAWordService.save(findAWordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, findAWordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /find-a-words : get all the findAWords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of findAWords in body
     */
    @GetMapping("/find-a-words")
    @Timed
    public ResponseEntity<List<FindAWordDTO>> getAllFindAWords(Pageable pageable) {
        log.debug("REST request to get a page of FindAWords");
        Page<FindAWordDTO> page = findAWordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/find-a-words");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /find-a-words/:id : get the "id" findAWord.
     *
     * @param id the id of the findAWordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the findAWordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/find-a-words/{id}")
    @Timed
    public ResponseEntity<FindAWordDTO> getFindAWord(@PathVariable Long id) {
        log.debug("REST request to get FindAWord : {}", id);
        Optional<FindAWordDTO> findAWordDTO = findAWordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findAWordDTO);
    }

    /**
     * DELETE  /find-a-words/:id : delete the "id" findAWord.
     *
     * @param id the id of the findAWordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/find-a-words/{id}")
    @Timed
    public ResponseEntity<Void> deleteFindAWord(@PathVariable Long id) {
        log.debug("REST request to delete FindAWord : {}", id);
        findAWordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
