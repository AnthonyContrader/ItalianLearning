package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.Hangman;
import com.italianlearning_microservices.contrader.domain.Category;
import com.italianlearning_microservices.contrader.domain.Level;
import com.italianlearning_microservices.contrader.repository.HangmanRepository;
import com.italianlearning_microservices.contrader.service.HangmanService;
import com.italianlearning_microservices.contrader.service.dto.HangmanDTO;
import com.italianlearning_microservices.contrader.service.mapper.HangmanMapper;
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
 * Test class for the HangmanResource REST controller.
 *
 * @see HangmanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class HangmanResourceIntTest {

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    private static final String DEFAULT_SENTENCE = "AAAAAAAAAA";
    private static final String UPDATED_SENTENCE = "BBBBBBBBBB";

    @Autowired
    private HangmanRepository hangmanRepository;


    @Autowired
    private HangmanMapper hangmanMapper;
    

    @Autowired
    private HangmanService hangmanService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHangmanMockMvc;

    private Hangman hangman;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HangmanResource hangmanResource = new HangmanResource(hangmanService);
        this.restHangmanMockMvc = MockMvcBuilders.standaloneSetup(hangmanResource)
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
    public static Hangman createEntity(EntityManager em) {
        Hangman hangman = new Hangman()
            .solution(DEFAULT_SOLUTION)
            .definition(DEFAULT_DEFINITION)
            .sentence(DEFAULT_SENTENCE);
        // Add required entity
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        hangman.setCategory(category);
        // Add required entity
        Level level = LevelResourceIntTest.createEntity(em);
        em.persist(level);
        em.flush();
        hangman.setLevel(level);
        return hangman;
    }

    @Before
    public void initTest() {
        hangman = createEntity(em);
    }

    @Test
    @Transactional
    public void createHangman() throws Exception {
        int databaseSizeBeforeCreate = hangmanRepository.findAll().size();

        // Create the Hangman
        HangmanDTO hangmanDTO = hangmanMapper.toDto(hangman);
        restHangmanMockMvc.perform(post("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isCreated());

        // Validate the Hangman in the database
        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeCreate + 1);
        Hangman testHangman = hangmanList.get(hangmanList.size() - 1);
        assertThat(testHangman.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testHangman.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
        assertThat(testHangman.getSentence()).isEqualTo(DEFAULT_SENTENCE);
    }

    @Test
    @Transactional
    public void createHangmanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hangmanRepository.findAll().size();

        // Create the Hangman with an existing ID
        hangman.setId(1L);
        HangmanDTO hangmanDTO = hangmanMapper.toDto(hangman);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHangmanMockMvc.perform(post("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hangman in the database
        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangmanRepository.findAll().size();
        // set the field null
        hangman.setSolution(null);

        // Create the Hangman, which fails.
        HangmanDTO hangmanDTO = hangmanMapper.toDto(hangman);

        restHangmanMockMvc.perform(post("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isBadRequest());

        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSentenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = hangmanRepository.findAll().size();
        // set the field null
        hangman.setSentence(null);

        // Create the Hangman, which fails.
        HangmanDTO hangmanDTO = hangmanMapper.toDto(hangman);

        restHangmanMockMvc.perform(post("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isBadRequest());

        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHangmen() throws Exception {
        // Initialize the database
        hangmanRepository.saveAndFlush(hangman);

        // Get all the hangmanList
        restHangmanMockMvc.perform(get("/api/hangmen?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hangman.getId().intValue())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION.toString())))
            .andExpect(jsonPath("$.[*].sentence").value(hasItem(DEFAULT_SENTENCE.toString())));
    }
    

    @Test
    @Transactional
    public void getHangman() throws Exception {
        // Initialize the database
        hangmanRepository.saveAndFlush(hangman);

        // Get the hangman
        restHangmanMockMvc.perform(get("/api/hangmen/{id}", hangman.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hangman.getId().intValue()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.sentence").value(DEFAULT_SENTENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingHangman() throws Exception {
        // Get the hangman
        restHangmanMockMvc.perform(get("/api/hangmen/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHangman() throws Exception {
        // Initialize the database
        hangmanRepository.saveAndFlush(hangman);

        int databaseSizeBeforeUpdate = hangmanRepository.findAll().size();

        // Update the hangman
        Hangman updatedHangman = hangmanRepository.findById(hangman.getId()).get();
        // Disconnect from session so that the updates on updatedHangman are not directly saved in db
        em.detach(updatedHangman);
        updatedHangman
            .solution(UPDATED_SOLUTION)
            .definition(UPDATED_DEFINITION)
            .sentence(UPDATED_SENTENCE);
        HangmanDTO hangmanDTO = hangmanMapper.toDto(updatedHangman);

        restHangmanMockMvc.perform(put("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isOk());

        // Validate the Hangman in the database
        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeUpdate);
        Hangman testHangman = hangmanList.get(hangmanList.size() - 1);
        assertThat(testHangman.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testHangman.getDefinition()).isEqualTo(UPDATED_DEFINITION);
        assertThat(testHangman.getSentence()).isEqualTo(UPDATED_SENTENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingHangman() throws Exception {
        int databaseSizeBeforeUpdate = hangmanRepository.findAll().size();

        // Create the Hangman
        HangmanDTO hangmanDTO = hangmanMapper.toDto(hangman);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restHangmanMockMvc.perform(put("/api/hangmen")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hangmanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hangman in the database
        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHangman() throws Exception {
        // Initialize the database
        hangmanRepository.saveAndFlush(hangman);

        int databaseSizeBeforeDelete = hangmanRepository.findAll().size();

        // Get the hangman
        restHangmanMockMvc.perform(delete("/api/hangmen/{id}", hangman.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hangman> hangmanList = hangmanRepository.findAll();
        assertThat(hangmanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hangman.class);
        Hangman hangman1 = new Hangman();
        hangman1.setId(1L);
        Hangman hangman2 = new Hangman();
        hangman2.setId(hangman1.getId());
        assertThat(hangman1).isEqualTo(hangman2);
        hangman2.setId(2L);
        assertThat(hangman1).isNotEqualTo(hangman2);
        hangman1.setId(null);
        assertThat(hangman1).isNotEqualTo(hangman2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HangmanDTO.class);
        HangmanDTO hangmanDTO1 = new HangmanDTO();
        hangmanDTO1.setId(1L);
        HangmanDTO hangmanDTO2 = new HangmanDTO();
        assertThat(hangmanDTO1).isNotEqualTo(hangmanDTO2);
        hangmanDTO2.setId(hangmanDTO1.getId());
        assertThat(hangmanDTO1).isEqualTo(hangmanDTO2);
        hangmanDTO2.setId(2L);
        assertThat(hangmanDTO1).isNotEqualTo(hangmanDTO2);
        hangmanDTO1.setId(null);
        assertThat(hangmanDTO1).isNotEqualTo(hangmanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(hangmanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(hangmanMapper.fromId(null)).isNull();
    }
}
