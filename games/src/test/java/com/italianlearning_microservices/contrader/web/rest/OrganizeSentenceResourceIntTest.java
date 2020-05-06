package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.OrganizeSentence;
import com.italianlearning_microservices.contrader.domain.Level;
import com.italianlearning_microservices.contrader.domain.Category;
import com.italianlearning_microservices.contrader.repository.OrganizeSentenceRepository;
import com.italianlearning_microservices.contrader.service.OrganizeSentenceService;
import com.italianlearning_microservices.contrader.service.dto.OrganizeSentenceDTO;
import com.italianlearning_microservices.contrader.service.mapper.OrganizeSentenceMapper;
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

import javax.persistence.EntityManager;
import java.util.List;


import static com.italianlearning_microservices.contrader.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrganizeSentenceResource REST controller.
 *
 * @see OrganizeSentenceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class OrganizeSentenceResourceIntTest {

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    private static final String DEFAULT_SENTENCE = "AAAAAAAAAA";
    private static final String UPDATED_SENTENCE = "BBBBBBBBBB";

    @Autowired
    private OrganizeSentenceRepository organizeSentenceRepository;


    @Autowired
    private OrganizeSentenceMapper organizeSentenceMapper;
    

    @Autowired
    private OrganizeSentenceService organizeSentenceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrganizeSentenceMockMvc;

    private OrganizeSentence organizeSentence;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrganizeSentenceResource organizeSentenceResource = new OrganizeSentenceResource(organizeSentenceService);
        this.restOrganizeSentenceMockMvc = MockMvcBuilders.standaloneSetup(organizeSentenceResource)
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
    public static OrganizeSentence createEntity(EntityManager em) {
        OrganizeSentence organizeSentence = new OrganizeSentence()
            .solution(DEFAULT_SOLUTION)
            .definition(DEFAULT_DEFINITION)
            .sentence(DEFAULT_SENTENCE);
        // Add required entity
        Level level = LevelResourceIntTest.createEntity(em);
        em.persist(level);
        em.flush();
        organizeSentence.setLevel(level);
        // Add required entity
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        organizeSentence.setCategory(category);
        return organizeSentence;
    }

    @Before
    public void initTest() {
        organizeSentence = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganizeSentence() throws Exception {
        int databaseSizeBeforeCreate = organizeSentenceRepository.findAll().size();

        // Create the OrganizeSentence
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(organizeSentence);
        restOrganizeSentenceMockMvc.perform(post("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isCreated());

        // Validate the OrganizeSentence in the database
        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeCreate + 1);
        OrganizeSentence testOrganizeSentence = organizeSentenceList.get(organizeSentenceList.size() - 1);
        assertThat(testOrganizeSentence.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testOrganizeSentence.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
        assertThat(testOrganizeSentence.getSentence()).isEqualTo(DEFAULT_SENTENCE);
    }

    @Test
    @Transactional
    public void createOrganizeSentenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizeSentenceRepository.findAll().size();

        // Create the OrganizeSentence with an existing ID
        organizeSentence.setId(1L);
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(organizeSentence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizeSentenceMockMvc.perform(post("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizeSentence in the database
        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizeSentenceRepository.findAll().size();
        // set the field null
        organizeSentence.setSolution(null);

        // Create the OrganizeSentence, which fails.
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(organizeSentence);

        restOrganizeSentenceMockMvc.perform(post("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isBadRequest());

        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSentenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizeSentenceRepository.findAll().size();
        // set the field null
        organizeSentence.setSentence(null);

        // Create the OrganizeSentence, which fails.
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(organizeSentence);

        restOrganizeSentenceMockMvc.perform(post("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isBadRequest());

        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganizeSentences() throws Exception {
        // Initialize the database
        organizeSentenceRepository.saveAndFlush(organizeSentence);

        // Get all the organizeSentenceList
        restOrganizeSentenceMockMvc.perform(get("/api/organize-sentences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizeSentence.getId().intValue())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION.toString())))
            .andExpect(jsonPath("$.[*].sentence").value(hasItem(DEFAULT_SENTENCE.toString())));
    }
    

    @Test
    @Transactional
    public void getOrganizeSentence() throws Exception {
        // Initialize the database
        organizeSentenceRepository.saveAndFlush(organizeSentence);

        // Get the organizeSentence
        restOrganizeSentenceMockMvc.perform(get("/api/organize-sentences/{id}", organizeSentence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(organizeSentence.getId().intValue()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.sentence").value(DEFAULT_SENTENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOrganizeSentence() throws Exception {
        // Get the organizeSentence
        restOrganizeSentenceMockMvc.perform(get("/api/organize-sentences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizeSentence() throws Exception {
        // Initialize the database
        organizeSentenceRepository.saveAndFlush(organizeSentence);

        int databaseSizeBeforeUpdate = organizeSentenceRepository.findAll().size();

        // Update the organizeSentence
        OrganizeSentence updatedOrganizeSentence = organizeSentenceRepository.findById(organizeSentence.getId()).get();
        // Disconnect from session so that the updates on updatedOrganizeSentence are not directly saved in db
        em.detach(updatedOrganizeSentence);
        updatedOrganizeSentence
            .solution(UPDATED_SOLUTION)
            .definition(UPDATED_DEFINITION)
            .sentence(UPDATED_SENTENCE);
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(updatedOrganizeSentence);

        restOrganizeSentenceMockMvc.perform(put("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isOk());

        // Validate the OrganizeSentence in the database
        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeUpdate);
        OrganizeSentence testOrganizeSentence = organizeSentenceList.get(organizeSentenceList.size() - 1);
        assertThat(testOrganizeSentence.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testOrganizeSentence.getDefinition()).isEqualTo(UPDATED_DEFINITION);
        assertThat(testOrganizeSentence.getSentence()).isEqualTo(UPDATED_SENTENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganizeSentence() throws Exception {
        int databaseSizeBeforeUpdate = organizeSentenceRepository.findAll().size();

        // Create the OrganizeSentence
        OrganizeSentenceDTO organizeSentenceDTO = organizeSentenceMapper.toDto(organizeSentence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restOrganizeSentenceMockMvc.perform(put("/api/organize-sentences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(organizeSentenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrganizeSentence in the database
        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrganizeSentence() throws Exception {
        // Initialize the database
        organizeSentenceRepository.saveAndFlush(organizeSentence);

        int databaseSizeBeforeDelete = organizeSentenceRepository.findAll().size();

        // Get the organizeSentence
        restOrganizeSentenceMockMvc.perform(delete("/api/organize-sentences/{id}", organizeSentence.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OrganizeSentence> organizeSentenceList = organizeSentenceRepository.findAll();
        assertThat(organizeSentenceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizeSentence.class);
        OrganizeSentence organizeSentence1 = new OrganizeSentence();
        organizeSentence1.setId(1L);
        OrganizeSentence organizeSentence2 = new OrganizeSentence();
        organizeSentence2.setId(organizeSentence1.getId());
        assertThat(organizeSentence1).isEqualTo(organizeSentence2);
        organizeSentence2.setId(2L);
        assertThat(organizeSentence1).isNotEqualTo(organizeSentence2);
        organizeSentence1.setId(null);
        assertThat(organizeSentence1).isNotEqualTo(organizeSentence2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizeSentenceDTO.class);
        OrganizeSentenceDTO organizeSentenceDTO1 = new OrganizeSentenceDTO();
        organizeSentenceDTO1.setId(1L);
        OrganizeSentenceDTO organizeSentenceDTO2 = new OrganizeSentenceDTO();
        assertThat(organizeSentenceDTO1).isNotEqualTo(organizeSentenceDTO2);
        organizeSentenceDTO2.setId(organizeSentenceDTO1.getId());
        assertThat(organizeSentenceDTO1).isEqualTo(organizeSentenceDTO2);
        organizeSentenceDTO2.setId(2L);
        assertThat(organizeSentenceDTO1).isNotEqualTo(organizeSentenceDTO2);
        organizeSentenceDTO1.setId(null);
        assertThat(organizeSentenceDTO1).isNotEqualTo(organizeSentenceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(organizeSentenceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(organizeSentenceMapper.fromId(null)).isNull();
    }
}
