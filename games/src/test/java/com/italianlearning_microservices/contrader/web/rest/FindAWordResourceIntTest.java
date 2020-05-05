package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.FindAWord;
import com.italianlearning_microservices.contrader.domain.Category;
import com.italianlearning_microservices.contrader.domain.Level;
import com.italianlearning_microservices.contrader.repository.FindAWordRepository;
import com.italianlearning_microservices.contrader.service.FindAWordService;
import com.italianlearning_microservices.contrader.service.dto.FindAWordDTO;
import com.italianlearning_microservices.contrader.service.mapper.FindAWordMapper;
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
 * Test class for the FindAWordResource REST controller.
 *
 * @see FindAWordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class FindAWordResourceIntTest {

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    private static final String DEFAULT_SENTENCE = "AAAAAAAAAA";
    private static final String UPDATED_SENTENCE = "BBBBBBBBBB";

    @Autowired
    private FindAWordRepository findAWordRepository;


    @Autowired
    private FindAWordMapper findAWordMapper;
    

    @Autowired
    private FindAWordService findAWordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFindAWordMockMvc;

    private FindAWord findAWord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FindAWordResource findAWordResource = new FindAWordResource(findAWordService);
        this.restFindAWordMockMvc = MockMvcBuilders.standaloneSetup(findAWordResource)
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
    public static FindAWord createEntity(EntityManager em) {
        FindAWord findAWord = new FindAWord()
            .solution(DEFAULT_SOLUTION)
            .definition(DEFAULT_DEFINITION)
            .sentence(DEFAULT_SENTENCE);
        // Add required entity
        Category category = CategoryResourceIntTest.createEntity(em);
        em.persist(category);
        em.flush();
        findAWord.setCategory(category);
        // Add required entity
        Level level = LevelResourceIntTest.createEntity(em);
        em.persist(level);
        em.flush();
        findAWord.setLevel(level);
        return findAWord;
    }

    @Before
    public void initTest() {
        findAWord = createEntity(em);
    }

    @Test
    @Transactional
    public void createFindAWord() throws Exception {
        int databaseSizeBeforeCreate = findAWordRepository.findAll().size();

        // Create the FindAWord
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(findAWord);
        restFindAWordMockMvc.perform(post("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isCreated());

        // Validate the FindAWord in the database
        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeCreate + 1);
        FindAWord testFindAWord = findAWordList.get(findAWordList.size() - 1);
        assertThat(testFindAWord.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testFindAWord.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
        assertThat(testFindAWord.getSentence()).isEqualTo(DEFAULT_SENTENCE);
    }

    @Test
    @Transactional
    public void createFindAWordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = findAWordRepository.findAll().size();

        // Create the FindAWord with an existing ID
        findAWord.setId(1L);
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(findAWord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFindAWordMockMvc.perform(post("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FindAWord in the database
        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = findAWordRepository.findAll().size();
        // set the field null
        findAWord.setSolution(null);

        // Create the FindAWord, which fails.
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(findAWord);

        restFindAWordMockMvc.perform(post("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isBadRequest());

        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSentenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = findAWordRepository.findAll().size();
        // set the field null
        findAWord.setSentence(null);

        // Create the FindAWord, which fails.
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(findAWord);

        restFindAWordMockMvc.perform(post("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isBadRequest());

        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFindAWords() throws Exception {
        // Initialize the database
        findAWordRepository.saveAndFlush(findAWord);

        // Get all the findAWordList
        restFindAWordMockMvc.perform(get("/api/find-a-words?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(findAWord.getId().intValue())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION.toString())))
            .andExpect(jsonPath("$.[*].sentence").value(hasItem(DEFAULT_SENTENCE.toString())));
    }
    

    @Test
    @Transactional
    public void getFindAWord() throws Exception {
        // Initialize the database
        findAWordRepository.saveAndFlush(findAWord);

        // Get the findAWord
        restFindAWordMockMvc.perform(get("/api/find-a-words/{id}", findAWord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(findAWord.getId().intValue()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION.toString()))
            .andExpect(jsonPath("$.sentence").value(DEFAULT_SENTENCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFindAWord() throws Exception {
        // Get the findAWord
        restFindAWordMockMvc.perform(get("/api/find-a-words/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFindAWord() throws Exception {
        // Initialize the database
        findAWordRepository.saveAndFlush(findAWord);

        int databaseSizeBeforeUpdate = findAWordRepository.findAll().size();

        // Update the findAWord
        FindAWord updatedFindAWord = findAWordRepository.findById(findAWord.getId()).get();
        // Disconnect from session so that the updates on updatedFindAWord are not directly saved in db
        em.detach(updatedFindAWord);
        updatedFindAWord
            .solution(UPDATED_SOLUTION)
            .definition(UPDATED_DEFINITION)
            .sentence(UPDATED_SENTENCE);
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(updatedFindAWord);

        restFindAWordMockMvc.perform(put("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isOk());

        // Validate the FindAWord in the database
        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeUpdate);
        FindAWord testFindAWord = findAWordList.get(findAWordList.size() - 1);
        assertThat(testFindAWord.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testFindAWord.getDefinition()).isEqualTo(UPDATED_DEFINITION);
        assertThat(testFindAWord.getSentence()).isEqualTo(UPDATED_SENTENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingFindAWord() throws Exception {
        int databaseSizeBeforeUpdate = findAWordRepository.findAll().size();

        // Create the FindAWord
        FindAWordDTO findAWordDTO = findAWordMapper.toDto(findAWord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restFindAWordMockMvc.perform(put("/api/find-a-words")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(findAWordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FindAWord in the database
        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFindAWord() throws Exception {
        // Initialize the database
        findAWordRepository.saveAndFlush(findAWord);

        int databaseSizeBeforeDelete = findAWordRepository.findAll().size();

        // Get the findAWord
        restFindAWordMockMvc.perform(delete("/api/find-a-words/{id}", findAWord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FindAWord> findAWordList = findAWordRepository.findAll();
        assertThat(findAWordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindAWord.class);
        FindAWord findAWord1 = new FindAWord();
        findAWord1.setId(1L);
        FindAWord findAWord2 = new FindAWord();
        findAWord2.setId(findAWord1.getId());
        assertThat(findAWord1).isEqualTo(findAWord2);
        findAWord2.setId(2L);
        assertThat(findAWord1).isNotEqualTo(findAWord2);
        findAWord1.setId(null);
        assertThat(findAWord1).isNotEqualTo(findAWord2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FindAWordDTO.class);
        FindAWordDTO findAWordDTO1 = new FindAWordDTO();
        findAWordDTO1.setId(1L);
        FindAWordDTO findAWordDTO2 = new FindAWordDTO();
        assertThat(findAWordDTO1).isNotEqualTo(findAWordDTO2);
        findAWordDTO2.setId(findAWordDTO1.getId());
        assertThat(findAWordDTO1).isEqualTo(findAWordDTO2);
        findAWordDTO2.setId(2L);
        assertThat(findAWordDTO1).isNotEqualTo(findAWordDTO2);
        findAWordDTO1.setId(null);
        assertThat(findAWordDTO1).isNotEqualTo(findAWordDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(findAWordMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(findAWordMapper.fromId(null)).isNull();
    }
}
