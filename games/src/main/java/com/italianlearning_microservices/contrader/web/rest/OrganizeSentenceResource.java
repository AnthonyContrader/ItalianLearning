package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.OrganizeSentenceService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.OrganizeSentenceDTO;
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
 * REST controller for managing OrganizeSentence.
 */
@RestController
@RequestMapping("/api")
public class OrganizeSentenceResource {

    private final Logger log = LoggerFactory.getLogger(OrganizeSentenceResource.class);

    private static final String ENTITY_NAME = "organizeSentence";

    private final OrganizeSentenceService organizeSentenceService;

    public OrganizeSentenceResource(OrganizeSentenceService organizeSentenceService) {
        this.organizeSentenceService = organizeSentenceService;
    }

    /**
     * POST  /organize-sentences : Create a new organizeSentence.
     *
     * @param organizeSentenceDTO the organizeSentenceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizeSentenceDTO, or with status 400 (Bad Request) if the organizeSentence has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organize-sentences")
    @Timed
    public ResponseEntity<OrganizeSentenceDTO> createOrganizeSentence(@Valid @RequestBody OrganizeSentenceDTO organizeSentenceDTO) throws URISyntaxException {
        log.debug("REST request to save OrganizeSentence : {}", organizeSentenceDTO);
        if (organizeSentenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new organizeSentence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizeSentenceDTO result = organizeSentenceService.save(organizeSentenceDTO);
        return ResponseEntity.created(new URI("/api/organize-sentences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organize-sentences : Updates an existing organizeSentence.
     *
     * @param organizeSentenceDTO the organizeSentenceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizeSentenceDTO,
     * or with status 400 (Bad Request) if the organizeSentenceDTO is not valid,
     * or with status 500 (Internal Server Error) if the organizeSentenceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organize-sentences")
    @Timed
    public ResponseEntity<OrganizeSentenceDTO> updateOrganizeSentence(@Valid @RequestBody OrganizeSentenceDTO organizeSentenceDTO) throws URISyntaxException {
        log.debug("REST request to update OrganizeSentence : {}", organizeSentenceDTO);
        if (organizeSentenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganizeSentenceDTO result = organizeSentenceService.save(organizeSentenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizeSentenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organize-sentences : get all the organizeSentences.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of organizeSentences in body
     */
    @GetMapping("/organize-sentences")
    @Timed
    public ResponseEntity<List<OrganizeSentenceDTO>> getAllOrganizeSentences(Pageable pageable) {
        log.debug("REST request to get a page of OrganizeSentences");
        Page<OrganizeSentenceDTO> page = organizeSentenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/organize-sentences");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /organize-sentences/:id : get the "id" organizeSentence.
     *
     * @param id the id of the organizeSentenceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizeSentenceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/organize-sentences/{id}")
    @Timed
    public ResponseEntity<OrganizeSentenceDTO> getOrganizeSentence(@PathVariable Long id) {
        log.debug("REST request to get OrganizeSentence : {}", id);
        Optional<OrganizeSentenceDTO> organizeSentenceDTO = organizeSentenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizeSentenceDTO);
    }

    /**
     * DELETE  /organize-sentences/:id : delete the "id" organizeSentence.
     *
     * @param id the id of the organizeSentenceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organize-sentences/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganizeSentence(@PathVariable Long id) {
        log.debug("REST request to delete OrganizeSentence : {}", id);
        organizeSentenceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
