package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.FindMistakeService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.FindMistakeDTO;
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
 * REST controller for managing FindMistake.
 */
@RestController
@RequestMapping("/api")
public class FindMistakeResource {

    private final Logger log = LoggerFactory.getLogger(FindMistakeResource.class);

    private static final String ENTITY_NAME = "findMistake";

    private final FindMistakeService findMistakeService;

    public FindMistakeResource(FindMistakeService findMistakeService) {
        this.findMistakeService = findMistakeService;
    }

    /**
     * POST  /find-mistakes : Create a new findMistake.
     *
     * @param findMistakeDTO the findMistakeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new findMistakeDTO, or with status 400 (Bad Request) if the findMistake has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/find-mistakes")
    @Timed
    public ResponseEntity<FindMistakeDTO> createFindMistake(@Valid @RequestBody FindMistakeDTO findMistakeDTO) throws URISyntaxException {
        log.debug("REST request to save FindMistake : {}", findMistakeDTO);
        if (findMistakeDTO.getId() != null) {
            throw new BadRequestAlertException("A new findMistake cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindMistakeDTO result = findMistakeService.save(findMistakeDTO);
        return ResponseEntity.created(new URI("/api/find-mistakes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /find-mistakes : Updates an existing findMistake.
     *
     * @param findMistakeDTO the findMistakeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated findMistakeDTO,
     * or with status 400 (Bad Request) if the findMistakeDTO is not valid,
     * or with status 500 (Internal Server Error) if the findMistakeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/find-mistakes")
    @Timed
    public ResponseEntity<FindMistakeDTO> updateFindMistake(@Valid @RequestBody FindMistakeDTO findMistakeDTO) throws URISyntaxException {
        log.debug("REST request to update FindMistake : {}", findMistakeDTO);
        if (findMistakeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindMistakeDTO result = findMistakeService.save(findMistakeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, findMistakeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /find-mistakes : get all the findMistakes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of findMistakes in body
     */
    @GetMapping("/find-mistakes")
    @Timed
    public ResponseEntity<List<FindMistakeDTO>> getAllFindMistakes(Pageable pageable) {
        log.debug("REST request to get a page of FindMistakes");
        Page<FindMistakeDTO> page = findMistakeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/find-mistakes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /find-mistakes/:id : get the "id" findMistake.
     *
     * @param id the id of the findMistakeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the findMistakeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/find-mistakes/{id}")
    @Timed
    public ResponseEntity<FindMistakeDTO> getFindMistake(@PathVariable Long id) {
        log.debug("REST request to get FindMistake : {}", id);
        Optional<FindMistakeDTO> findMistakeDTO = findMistakeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findMistakeDTO);
    }

    /**
     * DELETE  /find-mistakes/:id : delete the "id" findMistake.
     *
     * @param id the id of the findMistakeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/find-mistakes/{id}")
    @Timed
    public ResponseEntity<Void> deleteFindMistake(@PathVariable Long id) {
        log.debug("REST request to delete FindMistake : {}", id);
        findMistakeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
