package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.GuessPicture;
import com.italianlearning_microservices.contrader.domain.Level;
import com.italianlearning_microservices.contrader.domain.Category;
import com.italianlearning_microservices.contrader.repository.GuessPictureRepository;
import com.italianlearning_microservices.contrader.service.GuessPictureService;
import com.italianlearning_microservices.contrader.service.dto.GuessPictureDTO;
import com.italianlearning_microservices.contrader.service.mapper.GuessPictureMapper;
import com.italianlearning_microservices.contrader.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static com.italianlearning_microservices.contrader.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GuessPictureResource REST controller.
 *
 * @see GuessPictureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class GuessPictureResourceIntTest {

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private GuessPictureRepository guessPictureRepository;


    @Autowired
    private GuessPictureMapper guessPictureMapper;
    

    @Autowired
    private GuessPictureService guessPictureService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGuessPictureMockMvc;

    private GuessPicture guessPicture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GuessPictureResource guessPictureResource = new GuessPictureResource(guessPictureService);
        this.restGuessPictureMockMvc = MockMvcBuilders.standaloneSetup(guessPictureResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GuessPicture createEntity(EntityManager em) {
        GuessPicture guessPicture = new GuessPicture()
            .solution(DEFAULT_SOLUTION)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        // Add required entity
        Level level = LevelResourceIntTest.createEntity(em);
        em.persist(level);
        em.flush();
        guessPicture.setLevel(level);
        // Add required entity
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        guessPicture.setCategory(category);
        return guessPicture;
    }

    @Before
    public void initTest() {
        guessPicture = createEntity(em);
    }

    @Test
    @Transactional
    public void createGuessPicture() throws Exception {
        int databaseSizeBeforeCreate = guessPictureRepository.findAll().size();

        // Create the GuessPicture
        GuessPictureDTO guessPictureDTO = guessPictureMapper.toDto(guessPicture);
        restGuessPictureMockMvc.perform(post("/api/guess-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guessPictureDTO)))
            .andExpect(status().isCreated());

        // Validate the GuessPicture in the database
        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeCreate + 1);
        GuessPicture testGuessPicture = guessPictureList.get(guessPictureList.size() - 1);
        assertThat(testGuessPicture.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testGuessPicture.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testGuessPicture.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createGuessPictureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = guessPictureRepository.findAll().size();

        // Create the GuessPicture with an existing ID
        guessPicture.setId(1L);
        GuessPictureDTO guessPictureDTO = guessPictureMapper.toDto(guessPicture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGuessPictureMockMvc.perform(post("/api/guess-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guessPictureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GuessPicture in the database
        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = guessPictureRepository.findAll().size();
        // set the field null
        guessPicture.setSolution(null);

        // Create the GuessPicture, which fails.
        GuessPictureDTO guessPictureDTO = guessPictureMapper.toDto(guessPicture);

        restGuessPictureMockMvc.perform(post("/api/guess-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guessPictureDTO)))
            .andExpect(status().isBadRequest());

        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGuessPictures() throws Exception {
        // Initialize the database
        guessPictureRepository.saveAndFlush(guessPicture);

        // Get all the guessPictureList
        restGuessPictureMockMvc.perform(get("/api/guess-pictures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(guessPicture.getId().intValue())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }
    

    @Test
    @Transactional
    public void getGuessPicture() throws Exception {
        // Initialize the database
        guessPictureRepository.saveAndFlush(guessPicture);

        // Get the guessPicture
        restGuessPictureMockMvc.perform(get("/api/guess-pictures/{id}", guessPicture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(guessPicture.getId().intValue()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }
    @Test
    @Transactional
    public void getNonExistingGuessPicture() throws Exception {
        // Get the guessPicture
        restGuessPictureMockMvc.perform(get("/api/guess-pictures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGuessPicture() throws Exception {
        // Initialize the database
        guessPictureRepository.saveAndFlush(guessPicture);

        int databaseSizeBeforeUpdate = guessPictureRepository.findAll().size();

        // Update the guessPicture
        GuessPicture updatedGuessPicture = guessPictureRepository.findById(guessPicture.getId()).get();
        // Disconnect from session so that the updates on updatedGuessPicture are not directly saved in db
        em.detach(updatedGuessPicture);
        updatedGuessPicture
            .solution(UPDATED_SOLUTION)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        GuessPictureDTO guessPictureDTO = guessPictureMapper.toDto(updatedGuessPicture);

        restGuessPictureMockMvc.perform(put("/api/guess-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guessPictureDTO)))
            .andExpect(status().isOk());

        // Validate the GuessPicture in the database
        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeUpdate);
        GuessPicture testGuessPicture = guessPictureList.get(guessPictureList.size() - 1);
        assertThat(testGuessPicture.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testGuessPicture.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testGuessPicture.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingGuessPicture() throws Exception {
        int databaseSizeBeforeUpdate = guessPictureRepository.findAll().size();

        // Create the GuessPicture
        GuessPictureDTO guessPictureDTO = guessPictureMapper.toDto(guessPicture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restGuessPictureMockMvc.perform(put("/api/guess-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(guessPictureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GuessPicture in the database
        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGuessPicture() throws Exception {
        // Initialize the database
        guessPictureRepository.saveAndFlush(guessPicture);

        int databaseSizeBeforeDelete = guessPictureRepository.findAll().size();

        // Get the guessPicture
        restGuessPictureMockMvc.perform(delete("/api/guess-pictures/{id}", guessPicture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GuessPicture> guessPictureList = guessPictureRepository.findAll();
        assertThat(guessPictureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuessPicture.class);
        GuessPicture guessPicture1 = new GuessPicture();
        guessPicture1.setId(1L);
        GuessPicture guessPicture2 = new GuessPicture();
        guessPicture2.setId(guessPicture1.getId());
        assertThat(guessPicture1).isEqualTo(guessPicture2);
        guessPicture2.setId(2L);
        assertThat(guessPicture1).isNotEqualTo(guessPicture2);
        guessPicture1.setId(null);
        assertThat(guessPicture1).isNotEqualTo(guessPicture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GuessPictureDTO.class);
        GuessPictureDTO guessPictureDTO1 = new GuessPictureDTO();
        guessPictureDTO1.setId(1L);
        GuessPictureDTO guessPictureDTO2 = new GuessPictureDTO();
        assertThat(guessPictureDTO1).isNotEqualTo(guessPictureDTO2);
        guessPictureDTO2.setId(guessPictureDTO1.getId());
        assertThat(guessPictureDTO1).isEqualTo(guessPictureDTO2);
        guessPictureDTO2.setId(2L);
        assertThat(guessPictureDTO1).isNotEqualTo(guessPictureDTO2);
        guessPictureDTO1.setId(null);
        assertThat(guessPictureDTO1).isNotEqualTo(guessPictureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(guessPictureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(guessPictureMapper.fromId(null)).isNull();
    }
}
