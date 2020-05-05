package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.FindMistake;
import com.italianlearning_microservices.contrader.domain.Category;
import com.italianlearning_microservices.contrader.domain.Level;
import com.italianlearning_microservices.contrader.repository.FindMistakeRepository;
import com.italianlearning_microservices.contrader.service.FindMistakeService;
import com.italianlearning_microservices.contrader.service.dto.FindMistakeDTO;
import com.italianlearning_microservices.contrader.service.mapper.FindMistakeMapper;
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
 * Test class for the FindMistakeResource REST controller.
 *
 * @see FindMistakeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class FindMistakeResourceIntTest {

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    private static final String DEFAULT_SENTENCE = "AAAAAAAAAA";
    private static final String UPDATED_SENTENCE = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_A = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_A = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_B = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_B = "BBBBBBBBBB";

    private static final String DEFAULT_OPTION_C = "AAAAAAAAAA";
    private static final String UPDATED_OPTION_C = "BBBBBBBBBB";

    @Autowired
    private FindMistakeRepository findMistakeRepository;


    @Autowired
    private FindMistakeMapper findMistakeMapper;
    

    @Autowired
    private FindMistakeService findMistakeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFindMistakeMockMvc;

    private FindMistake findMistake;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FindMistakeResource findMistakeResource = new FindMistakeResource(findMistakeService);
        this.restFindMistakeMockMvc = MockMvcBuilders.standaloneSetup(findMistakeResource)
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
    public static FindMistake createEntity(EntityManager em) {
        FindMistake findMistake = new FindMistake()
            .solution(DEFAULT_SOLUTION)
            .definition(DEFAULT_DEFINITION)
            .sentence(DEFAULT_SENTENCE)
            .optionA(DEFAULT_OPTION_A)
            .optionB(DEFAULT_OPTION_B)
            .optionC(DEFAULT_OPTION_C);
        // Add required entity
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        findMistake.setCategory(category);
        // Add required entity
        Level level = LevelResourceIntTest.createEntity(em);
        em.persist(level);
        em.flush();
        findMistake.setLevel(level);
        return findMistake;
    }

    @Before
    public void initTest() {
        findMistake = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindMistake() throws Exception {
        int databaseSizeBeforeCreate = findMistakeRepository.findAll().size();

        // Create the FindMistake
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);
        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isCreated());

        // Validate the FindMistake in the database
        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeCreate + 1);
        FindMistake testFindMistake = findMistakeList.get(findMistakeList.size() - 1);
        assertThat(testFindMistake.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testFindMistake.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
        assertThat(testFindMistake.getSentence()).isEqualTo(DEFAULT_SENTENCE);
        assertThat(testFindMistake.getOptionA()).isEqualTo(DEFAULT_OPTION_A);
        assertThat(testFindMistake.getOptionB()).isEqualTo(DEFAULT_OPTION_B);
        assertThat(testFindMistake.getOptionC()).isEqualTo(DEFAULT_OPTION_C);
    }

    @Test
    @Transactional
    public void createFindMistakeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findMistakeRepository.findAll().size();

        // Create the FindMistake with an existing ID
        findMistake.setId(1L);
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FindMistake in the database
        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = findMistakeRepository.findAll().size();
        // set the field null
        findMistake.setSolution(null);

        // Create the FindMistake, which fails.
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSentenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = findMistakeRepository.findAll().size();
        // set the field null
        findMistake.setSentence(null);

        // Create the FindMistake, which fails.
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOptionAIsRequired() throws Exception {
        int databaseSizeBeforeTest = findMistakeRepository.findAll().size();
        // set the field null
        findMistake.setOptionA(null);

        // Create the FindMistake, which fails.
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOptionBIsRequired() throws Exception {
        int databaseSizeBeforeTest = findMistakeRepository.findAll().size();
        // set the field null
        findMistake.setOptionB(null);

        // Create the FindMistake, which fails.
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOptionCIsRequired() throws Exception {
        int databaseSizeBeforeTest = findMistakeRepository.findAll().size();
        // set the field null
        findMistake.setOptionC(null);

        // Create the FindMistake, which fails.
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        restFindMistakeMockMvc.perform(post("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindMistakes() throws Exception {
        // Initialize the database
        findMistakeRepository.saveAndFlush(findMistake);

        // Get all the findMistakeList
        restFindMistakeMockMvc.perform(get("/api/find-mistakes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findMistake.getId().intValue())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION.toString())))
            .andExpect(jsonPath("$.[*].sentence").value(hasItem(DEFAULT_SENTENCE.toString())))
            .andExpect(jsonPath("$.[*].optionA").value(hasItem(DEFAULT_OPTION_A.toString())))
            .andExpect(jsonPath("$.[*].optionB").value(hasItem(DEFAULT_OPTION_B.toString())))
            .andExpect(jsonPath("$.[*].optionC").value(hasItem(DEFAULT_OPTION_C.toString())));
    }
    

    @Test
    @Transactional
    public void getFindMistake() throws Exception {
        // Initialize the database
        findMistakeRepository.saveAndFlush(findMistake);

        // Get the findMistake
        restFindMistakeMockMvc.perform(get("/api/find-mistakes/{id}", findMistake.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(findMistake.getId().intValue()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.sentence").value(DEFAULT_SENTENCE.toString()))
            .andExpect(jsonPath("$.optionA").value(DEFAULT_OPTION_A.toString()))
            .andExpect(jsonPath("$.optionB").value(DEFAULT_OPTION_B.toString()))
            .andExpect(jsonPath("$.optionC").value(DEFAULT_OPTION_C.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFindMistake() throws Exception {
        // Get the findMistake
        restFindMistakeMockMvc.perform(get("/api/find-mistakes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindMistake() throws Exception {
        // Initialize the database
        findMistakeRepository.saveAndFlush(findMistake);

        int databaseSizeBeforeUpdate = findMistakeRepository.findAll().size();

        // Update the findMistake
        FindMistake updatedFindMistake = findMistakeRepository.findById(findMistake.getId()).get();
        // Disconnect from session so that the updates on updatedFindMistake are not directly saved in db
        em.detach(updatedFindMistake);
        updatedFindMistake
            .solution(UPDATED_SOLUTION)
            .definition(UPDATED_DEFINITION)
            .sentence(UPDATED_SENTENCE)
            .optionA(UPDATED_OPTION_A)
            .optionB(UPDATED_OPTION_B)
            .optionC(UPDATED_OPTION_C);
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(updatedFindMistake);

        restFindMistakeMockMvc.perform(put("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isOk());

        // Validate the FindMistake in the database
        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeUpdate);
        FindMistake testFindMistake = findMistakeList.get(findMistakeList.size() - 1);
        assertThat(testFindMistake.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testFindMistake.getDefinition()).isEqualTo(UPDATED_DEFINITION);
        assertThat(testFindMistake.getSentence()).isEqualTo(UPDATED_SENTENCE);
        assertThat(testFindMistake.getOptionA()).isEqualTo(UPDATED_OPTION_A);
        assertThat(testFindMistake.getOptionB()).isEqualTo(UPDATED_OPTION_B);
        assertThat(testFindMistake.getOptionC()).isEqualTo(UPDATED_OPTION_C);
    }

    @Test
    @Transactional
    public void updateNonExistingFindMistake() throws Exception {
        int databaseSizeBeforeUpdate = findMistakeRepository.findAll().size();

        // Create the FindMistake
        FindMistakeDTO findMistakeDTO = findMistakeMapper.toDto(findMistake);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restFindMistakeMockMvc.perform(put("/api/find-mistakes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findMistakeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FindMistake in the database
        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindMistake() throws Exception {
        // Initialize the database
        findMistakeRepository.saveAndFlush(findMistake);

        int databaseSizeBeforeDelete = findMistakeRepository.findAll().size();

        // Get the findMistake
        restFindMistakeMockMvc.perform(delete("/api/find-mistakes/{id}", findMistake.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FindMistake> findMistakeList = findMistakeRepository.findAll();
        assertThat(findMistakeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindMistake.class);
        FindMistake findMistake1 = new FindMistake();
        findMistake1.setId(1L);
        FindMistake findMistake2 = new FindMistake();
        findMistake2.setId(findMistake1.getId());
        assertThat(findMistake1).isEqualTo(findMistake2);
        findMistake2.setId(2L);
        assertThat(findMistake1).isNotEqualTo(findMistake2);
        findMistake1.setId(null);
        assertThat(findMistake1).isNotEqualTo(findMistake2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindMistakeDTO.class);
        FindMistakeDTO findMistakeDTO1 = new FindMistakeDTO();
        findMistakeDTO1.setId(1L);
        FindMistakeDTO findMistakeDTO2 = new FindMistakeDTO();
        assertThat(findMistakeDTO1).isNotEqualTo(findMistakeDTO2);
        findMistakeDTO2.setId(findMistakeDTO1.getId());
        assertThat(findMistakeDTO1).isEqualTo(findMistakeDTO2);
        findMistakeDTO2.setId(2L);
        assertThat(findMistakeDTO1).isNotEqualTo(findMistakeDTO2);
        findMistakeDTO1.setId(null);
        assertThat(findMistakeDTO1).isNotEqualTo(findMistakeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(findMistakeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(findMistakeMapper.fromId(null)).isNull();
    }
}
