package com.italianlearning_microservices.contrader.web.rest;

import com.italianlearning_microservices.contrader.UserApp;

import com.italianlearning_microservices.contrader.domain.User;
import com.italianlearning_microservices.contrader.repository.UserRepository;
import com.italianlearning_microservices.contrader.service.UserService;
import com.italianlearning_microservices.contrader.service.dto.UserDTO;
import com.italianlearning_microservices.contrader.service.mapper.UserMapper;
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

import com.italianlearning_microservices.contrader.domain.enumeration.Usertype;
/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApp.class)
public class UserResourceIntTest {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Usertype DEFAULT_USERTYPE = Usertype.ADMIN;
    private static final Usertype UPDATED_USERTYPE = Usertype.USER;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;
    

    @Autowired
    private UserService userService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserMockMvc;

    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserResource userResource = new UserResource(userService);
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource)
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
    public static User createEntity(EntityManager em) {
        User user = new User()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .usertype(DEFAULT_USERTYPE);
        return user;
    }

    @Before
    public void initTest() {
        user = createEntity(em);
    }

    @Test
    @Transactional
    public void createUser() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();

        // Create the User
        UserDTO userDTO = userMapper.toDto(user);
        restUserMockMvc.perform(post("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isCreated());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate + 1);
        User testUser = userList.get(userList.size() - 1);
        assertThat(testUser.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUser.getUsertype()).isEqualTo(DEFAULT_USERTYPE);
    }

    @Test
    @Transactional
    public void createUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();

        // Create the User with an existing ID
        user.setId(1L);
        UserDTO userDTO = userMapper.toDto(user);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMockMvc.perform(post("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isBadRequest());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setUsername(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc.perform(post("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setPassword(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc.perform(post("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsertypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userRepository.findAll().size();
        // set the field null
        user.setUsertype(null);

        // Create the User, which fails.
        UserDTO userDTO = userMapper.toDto(user);

        restUserMockMvc.perform(post("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isBadRequest());

        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get all the userList
        restUserMockMvc.perform(get("/api/users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(user.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].usertype").value(hasItem(DEFAULT_USERTYPE.toString())));
    }
    

    @Test
    @Transactional
    public void getUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        // Get the user
        restUserMockMvc.perform(get("/api/users/{id}", user.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(user.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.usertype").value(DEFAULT_USERTYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUser() throws Exception {
        // Get the user
        restUserMockMvc.perform(get("/api/users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        int databaseSizeBeforeUpdate = userRepository.findAll().size();

        // Update the user
        User updatedUser = userRepository.findById(user.getId()).get();
        // Disconnect from session so that the updates on updatedUser are not directly saved in db
        em.detach(updatedUser);
        updatedUser
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .usertype(UPDATED_USERTYPE);
        UserDTO userDTO = userMapper.toDto(updatedUser);

        restUserMockMvc.perform(put("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isOk());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
        User testUser = userList.get(userList.size() - 1);
        assertThat(testUser.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUser.getUsertype()).isEqualTo(UPDATED_USERTYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingUser() throws Exception {
        int databaseSizeBeforeUpdate = userRepository.findAll().size();

        // Create the User
        UserDTO userDTO = userMapper.toDto(user);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restUserMockMvc.perform(put("/api/users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userDTO)))
            .andExpect(status().isBadRequest());

        // Validate the User in the database
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUser() throws Exception {
        // Initialize the database
        userRepository.saveAndFlush(user);

        int databaseSizeBeforeDelete = userRepository.findAll().size();

        // Get the user
        restUserMockMvc.perform(delete("/api/users/{id}", user.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User.class);
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(user1.getId());
        assertThat(user1).isEqualTo(user2);
        user2.setId(2L);
        assertThat(user1).isNotEqualTo(user2);
        user1.setId(null);
        assertThat(user1).isNotEqualTo(user2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDTO.class);
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(1L);
        UserDTO userDTO2 = new UserDTO();
        assertThat(userDTO1).isNotEqualTo(userDTO2);
        userDTO2.setId(userDTO1.getId());
        assertThat(userDTO1).isEqualTo(userDTO2);
        userDTO2.setId(2L);
        assertThat(userDTO1).isNotEqualTo(userDTO2);
        userDTO1.setId(null);
        assertThat(userDTO1).isNotEqualTo(userDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userMapper.fromId(null)).isNull();
    }
}
