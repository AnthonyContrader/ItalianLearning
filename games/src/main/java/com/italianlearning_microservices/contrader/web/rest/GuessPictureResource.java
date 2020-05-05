package com.italianlearning_microservices.contrader.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.italianlearning_microservices.contrader.service.GuessPictureService;
import com.italianlearning_microservices.contrader.web.rest.errors.BadRequestAlertException;
import com.italianlearning_microservices.contrader.web.rest.util.HeaderUtil;
import com.italianlearning_microservices.contrader.web.rest.util.PaginationUtil;
import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;
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
 * REST controller for managing GuessPicture.
 */
@RestController
@RequestMapping("/api")
public class GuessPictureResource {

    private final Logger log = LoggerFactory.getLogger(GuessPictureResource.class);

    private static final String ENTITY_NAME = "guessPicture";

    private final GuessPictureService guessPictureService;

    public GuessPictureResource(GuessPictureService guessPictureService) {
        this.guessPictureService = guessPictureService;
    }

    /**
     * POST  /guess-pictures : Create a new guessPicture.
     *
     * @param guessPictureDTO the guessPictureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new guessPictureDTO, or with status 400 (Bad Request) if the guessPicture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/guess-pictures")
    @Timed
    public ResponseEntity<GuessPictureDTO> createGuessPicture(@Valid @RequestBody GuessPictureDTO guessPictureDTO) throws URISyntaxException {
        log.debug("REST request to save GuessPicture : {}", guessPictureDTO);
        if (guessPictureDTO.getId() != null) {
            throw new BadRequestAlertException("A new guessPicture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GuessPictureDTO result = guessPictureService.save(guessPictureDTO);
        return ResponseEntity.created(new URI("/api/guess-pictures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /guess-pictures : Updates an existing guessPicture.
     *
     * @param guessPictureDTO the guessPictureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated guessPictureDTO,
     * or with status 400 (Bad Request) if the guessPictureDTO is not valid,
     * or with status 500 (Internal Server Error) if the guessPictureDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/guess-pictures")
    @Timed
    public ResponseEntity<GuessPictureDTO> updateGuessPicture(@Valid @RequestBody GuessPictureDTO guessPictureDTO) throws URISyntaxException {
        log.debug("REST request to update GuessPicture : {}", guessPictureDTO);
        if (guessPictureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GuessPictureDTO result = guessPictureService.save(guessPictureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, guessPictureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /guess-pictures : get all the guessPictures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of guessPictures in body
     */
    @GetMapping("/guess-pictures")
    @Timed
    public ResponseEntity<List<GuessPictureDTO>> getAllGuessPictures(Pageable pageable) {
        log.debug("REST request to get a page of GuessPictures");
        Page<GuessPictureDTO> page = guessPictureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/guess-pictures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /guess-pictures/:id : get the "id" guessPicture.
     *
     * @param id the id of the guessPictureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the guessPictureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/guess-pictures/{id}")
    @Timed
    public ResponseEntity<GuessPictureDTO> getGuessPicture(@PathVariable Long id) {
        log.debug("REST request to get GuessPicture : {}", id);
        Optional<GuessPictureDTO> guessPictureDTO = guessPictureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(guessPictureDTO);
    }

    /**
     * DELETE  /guess-pictures/:id : delete the "id" guessPicture.
     *
     * @param id the id of the guessPictureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/guess-pictures/{id}")
    @Timed
    public ResponseEntity<Void> deleteGuessPicture(@PathVariable Long id) {
        log.debug("REST request to delete GuessPicture : {}", id);
        guessPictureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
