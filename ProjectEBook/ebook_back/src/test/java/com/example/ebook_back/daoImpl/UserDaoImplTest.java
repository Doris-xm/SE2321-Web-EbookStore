package com.example.ebook_back.daoImpl;

import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.repository.UserAuthRepository;
import com.example.ebook_back.repository.UserRepository;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private UserAuthRepository userAuthRepository;

    @Mock
    private UserRepository userRepository;

    private List<UserAuth> userAuthList;
    private List<User> userList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userDao = new UserDaoImpl();
        ReflectionTestUtils.setField(userDao, "userAuthRepository", userAuthRepository);
        ReflectionTestUtils.setField(userDao, "userRepository", userRepository);

        //1 existing user
        User existing_user = new User();
        existing_user.setId(123);
        existing_user.setEmail("existing_user@example.com");
        UserAuth existing_userAuth = new UserAuth();
        existing_userAuth.setUserId(123);
        existing_userAuth.setUserName("existing_user");
        existing_userAuth.setPassword("correct_password");
        existing_userAuth.setUserMode(0);
        existing_userAuth.setBan(false);
        existing_userAuth.setUser(existing_user);

        //2 ban user
        User ban_user = new User();
        ban_user.setId(789);
        ban_user.setEmail("ban_user@example.com");
        UserAuth ban_userAuth = new UserAuth();
        ban_userAuth.setUserId(789);
        ban_userAuth.setUserName("ban_user");
        ban_userAuth.setPassword("correct_password");
        ban_userAuth.setUserMode(0);
        ban_userAuth.setBan(true);
        ban_userAuth.setUser(ban_user);

        //3 root user
        User root_user = new User();
        root_user.setId(110);
        root_user.setEmail("root_user@example.com");
        UserAuth root_userAuth = new UserAuth();
        root_userAuth.setUserId(110);
        root_userAuth.setUserName("root_user");
        root_userAuth.setPassword("correct_password");
        root_userAuth.setUserMode(1);
        root_userAuth.setBan(false);
        root_userAuth.setUser(root_user);

        userAuthList = new ArrayList<>();
        userAuthList.add(existing_userAuth);
        userAuthList.add(ban_userAuth);
        userAuthList.add(root_userAuth);

        userList = new ArrayList<>();
        userList.add(existing_user);
        userList.add(ban_user);
        userList.add(root_user);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/findByUserName.csv", numLinesToSkip = 1)
    void findByUserName(String name, int result_id) {
        Mockito.when(userAuthRepository.findByUserName(name)).thenReturn(userAuthList.stream().filter(userAuth -> userAuth.getUserName().equals(name)).findFirst().orElse(null));
        UserAuth userAuth = userDao.findByUserName(name);
        if (result_id < 0) {
            assertNull(userAuth);
        } else {
            assertEquals(result_id, userAuth.getUserId());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/findUserByMail.csv", numLinesToSkip = 1)
    void findUserByMail(String mail, int result_id) {
        Mockito.when(userRepository.findByEmail(mail)).thenReturn(userList.stream().filter(user -> user.getEmail().equals(mail)).findFirst().orElse(null));
        User user = userDao.findUserByMail(mail);
        if (result_id < 0) {
            assertNull(user);
        } else {
            assertEquals(result_id, user.getId());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/activateUser.csv", numLinesToSkip = 1)
    void activateUser(int userId, boolean result) {
        Mockito.when(userAuthRepository.findByUserId(userId)).thenReturn(userAuthList.stream().filter(userAuth -> userAuth.getUserId() == userId).findFirst().orElse(null));
        boolean success = userDao.activateUser(userId);
        assertEquals(result, success);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/findByUserName.csv", numLinesToSkip = 1)
    void checkName(String name, int result_id) {
        Mockito.when(userAuthRepository.findByUserName(name)).thenReturn(userAuthList.stream().filter(userAuth -> userAuth.getUserName().equals(name)).findFirst().orElse(null));
        boolean success = userDao.checkName(name);
        if (result_id < 0) {
            assertTrue(success);
        } else {
            assertFalse(success);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/findUserByMail.csv", numLinesToSkip = 1)
    void checkMail(String mail, int result_id) {
        Mockito.when(userRepository.findByEmail(mail)).thenReturn(userList.stream().filter(user -> user.getEmail().equals(mail)).findFirst().orElse(null));
        boolean success = userDao.checkMail(mail);
        if (result_id < 0) {
            assertTrue(success);
        } else {
            assertFalse(success);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/createUser.csv", numLinesToSkip = 1)
    void createUser(int userId, boolean result) {
        if(userId == 404) { //crash case
            User user = new User();
            user.setId(userId);
            Mockito.when(userRepository.save(user)).thenThrow(new RuntimeException());
            boolean success = userDao.createUser(user);
            assertEquals(result, success);
            return;
        }
        User user = new User();
        user.setId(userId);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        boolean success = userDao.createUser(user);
        assertEquals(result, success);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/createUser.csv", numLinesToSkip = 1)
    void createUserAuth(int userId, boolean result) {
        if (userId == 404) { //crash case
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(userId);
            Mockito.when(userAuthRepository.save(userAuth)).thenThrow(new RuntimeException());
            boolean success = userDao.createUserAuth(userAuth);
            assertEquals(result, success);
            return;
        }
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(userId);
        Mockito.when(userAuthRepository.save(userAuth)).thenReturn(userAuth);
        boolean success = userDao.createUserAuth(userAuth);
        assertEquals(result, success);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserDaoImpl-test-data/banUser.csv", numLinesToSkip = 1)
    void banUser(int userId, boolean result) {
        Mockito.when(userAuthRepository.findByUserId(userId)).thenReturn(userAuthList.stream().filter(userAuth -> userAuth.getUserId() == userId).findFirst().orElse(null));
        boolean success = userDao.banUser(userId, true);
        assertEquals(result, success);
    }

    @Test
    void getAllUsers() {
        List<UserAuth> userAuthListRet = new ArrayList<>();
        for (UserAuth userAuth : userAuthList) {
            if (userAuth.getUserMode() == 0) {
                userAuthListRet.add(userAuth);
            }
        }
        Mockito.when(userAuthRepository.findUserAuthByUserMode(0)).thenReturn(userAuthListRet);
        List<UserAuth> userAuths = userDao.getAllUsers();
        assertEquals(userAuthListRet, userAuths);
    }

    @Test
    void logout() {
        assertTrue(userDao.logout(123));
    }

    @Test
    void checkSession() {
        assertTrue(userDao.checkSession(123));
    }


    @Test
    void findUserAuthById() {
        Mockito.when(userAuthRepository.findByUserId(123)).thenReturn(userAuthList.get(0));
        UserAuth userAuth = userDao.findUserAuthById(123);
        assertEquals(userAuthList.get(0), userAuth);
    }
}