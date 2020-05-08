package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.GamesApp;

import com.italianlearning_microservices.contrader.domain.GamePlaylist;
import com.italianlearning_microservices.contrader.domain.Playlist;
import com.italianlearning_microservices.contrader.repository.GamePlaylistRepository;
import com.italianlearning_microservices.contrader.service.GamePlaylistService;
import com.italianlearning_microservices.contrader.service.dto.GamePlaylistDTO;
import com.italianlearning_microservices.contrader.service.mapper.GamePlaylistMapper;
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
 * Test class for the GamePlaylistResource REST controller.
 *
 * @see GamePlaylistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GamesApp.class)
public class GamePlaylistResourceIntTest {

    private static final String DEFAULT_TYPE_GAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_GAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_GAME = 1;
    private static final Integer UPDATED_ID_GAME = 2;

    @Autowired
    private GamePlaylistRepository gamePlaylistRepository;


    @Autowired
    private GamePlaylistMapper gamePlaylistMapper;
    

    @Autowired
    private GamePlaylistService gamePlaylistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGamePlaylistMockMvc;

    private GamePlaylist gamePlaylist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GamePlaylistResource gamePlaylistResource = new GamePlaylistResource(gamePlaylistService);
        this.restGamePlaylistMockMvc = MockMvcBuilders.standaloneSetup(gamePlaylistResource)
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
    public static GamePlaylist createEntity(EntityManager em) {
        GamePlaylist gamePlaylist = new GamePlaylist()
            .typeGame(DEFAULT_TYPE_GAME)
            .idGame(DEFAULT_ID_GAME);
        // Add required entity
        Playlist playlist = PlaylistResourceIntTest.createEntity(em);
        em.persist(playlist);
        em.flush();
        gamePlaylist.setPlaylist(playlist);
        return gamePlaylist;
    }

    @Before
    public void initTest() {
        gamePlaylist = createEntity(em);
    }

    @Test
    @Transactional
    public void createGamePlaylist() throws Exception {
        int databaseSizeBeforeCreate = gamePlaylistRepository.findAll().size();

        // Create the GamePlaylist
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(gamePlaylist);
        restGamePlaylistMockMvc.perform(post("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isCreated());

        // Validate the GamePlaylist in the database
        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeCreate + 1);
        GamePlaylist testGamePlaylist = gamePlaylistList.get(gamePlaylistList.size() - 1);
        assertThat(testGamePlaylist.getTypeGame()).isEqualTo(DEFAULT_TYPE_GAME);
        assertThat(testGamePlaylist.getIdGame()).isEqualTo(DEFAULT_ID_GAME);
    }

    @Test
    @Transactional
    public void createGamePlaylistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gamePlaylistRepository.findAll().size();

        // Create the GamePlaylist with an existing ID
        gamePlaylist.setId(1L);
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(gamePlaylist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGamePlaylistMockMvc.perform(post("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GamePlaylist in the database
        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeGameIsRequired() throws Exception {
        int databaseSizeBeforeTest = gamePlaylistRepository.findAll().size();
        // set the field null
        gamePlaylist.setTypeGame(null);

        // Create the GamePlaylist, which fails.
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(gamePlaylist);

        restGamePlaylistMockMvc.perform(post("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isBadRequest());

        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdGameIsRequired() throws Exception {
        int databaseSizeBeforeTest = gamePlaylistRepository.findAll().size();
        // set the field null
        gamePlaylist.setIdGame(null);

        // Create the GamePlaylist, which fails.
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(gamePlaylist);

        restGamePlaylistMockMvc.perform(post("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isBadRequest());

        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGamePlaylists() throws Exception {
        // Initialize the database
        gamePlaylistRepository.saveAndFlush(gamePlaylist);

        // Get all the gamePlaylistList
        restGamePlaylistMockMvc.perform(get("/api/game-playlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gamePlaylist.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeGame").value(hasItem(DEFAULT_TYPE_GAME.toString())))
            .andExpect(jsonPath("$.[*].idGame").value(hasItem(DEFAULT_ID_GAME)));
    }
    

    @Test
    @Transactional
    public void getGamePlaylist() throws Exception {
        // Initialize the database
        gamePlaylistRepository.saveAndFlush(gamePlaylist);

        // Get the gamePlaylist
        restGamePlaylistMockMvc.perform(get("/api/game-playlists/{id}", gamePlaylist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gamePlaylist.getId().intValue()))
            .andExpect(jsonPath("$.typeGame").value(DEFAULT_TYPE_GAME.toString()))
            .andExpect(jsonPath("$.idGame").value(DEFAULT_ID_GAME));
    }
    @Test
    @Transactional
    public void getNonExistingGamePlaylist() throws Exception {
        // Get the gamePlaylist
        restGamePlaylistMockMvc.perform(get("/api/game-playlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGamePlaylist() throws Exception {
        // Initialize the database
        gamePlaylistRepository.saveAndFlush(gamePlaylist);

        int databaseSizeBeforeUpdate = gamePlaylistRepository.findAll().size();

        // Update the gamePlaylist
        GamePlaylist updatedGamePlaylist = gamePlaylistRepository.findById(gamePlaylist.getId()).get();
        // Disconnect from session so that the updates on updatedGamePlaylist are not directly saved in db
        em.detach(updatedGamePlaylist);
        updatedGamePlaylist
            .typeGame(UPDATED_TYPE_GAME)
            .idGame(UPDATED_ID_GAME);
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(updatedGamePlaylist);

        restGamePlaylistMockMvc.perform(put("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isOk());

        // Validate the GamePlaylist in the database
        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeUpdate);
        GamePlaylist testGamePlaylist = gamePlaylistList.get(gamePlaylistList.size() - 1);
        assertThat(testGamePlaylist.getTypeGame()).isEqualTo(UPDATED_TYPE_GAME);
        assertThat(testGamePlaylist.getIdGame()).isEqualTo(UPDATED_ID_GAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGamePlaylist() throws Exception {
        int databaseSizeBeforeUpdate = gamePlaylistRepository.findAll().size();

        // Create the GamePlaylist
        GamePlaylistDTO gamePlaylistDTO = gamePlaylistMapper.toDto(gamePlaylist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restGamePlaylistMockMvc.perform(put("/api/game-playlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gamePlaylistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GamePlaylist in the database
        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGamePlaylist() throws Exception {
        // Initialize the database
        gamePlaylistRepository.saveAndFlush(gamePlaylist);

        int databaseSizeBeforeDelete = gamePlaylistRepository.findAll().size();

        // Get the gamePlaylist
        restGamePlaylistMockMvc.perform(delete("/api/game-playlists/{id}", gamePlaylist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<GamePlaylist> gamePlaylistList = gamePlaylistRepository.findAll();
        assertThat(gamePlaylistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GamePlaylist.class);
        GamePlaylist gamePlaylist1 = new GamePlaylist();
        gamePlaylist1.setId(1L);
        GamePlaylist gamePlaylist2 = new GamePlaylist();
        gamePlaylist2.setId(gamePlaylist1.getId());
        assertThat(gamePlaylist1).isEqualTo(gamePlaylist2);
        gamePlaylist2.setId(2L);
        assertThat(gamePlaylist1).isNotEqualTo(gamePlaylist2);
        gamePlaylist1.setId(null);
        assertThat(gamePlaylist1).isNotEqualTo(gamePlaylist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GamePlaylistDTO.class);
        GamePlaylistDTO gamePlaylistDTO1 = new GamePlaylistDTO();
        gamePlaylistDTO1.setId(1L);
        GamePlaylistDTO gamePlaylistDTO2 = new GamePlaylistDTO();
        assertThat(gamePlaylistDTO1).isNotEqualTo(gamePlaylistDTO2);
        gamePlaylistDTO2.setId(gamePlaylistDTO1.getId());
        assertThat(gamePlaylistDTO1).isEqualTo(gamePlaylistDTO2);
        gamePlaylistDTO2.setId(2L);
        assertThat(gamePlaylistDTO1).isNotEqualTo(gamePlaylistDTO2);
        gamePlaylistDTO1.setId(null);
        assertThat(gamePlaylistDTO1).isNotEqualTo(gamePlaylistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(gamePlaylistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(gamePlaylistMapper.fromId(null)).isNull();
    }
}
