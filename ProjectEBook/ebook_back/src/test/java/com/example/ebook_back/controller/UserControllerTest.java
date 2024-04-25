package com.example.ebook_back.controller;

import com.example.ebook_back.entity.User;
import com.example.ebook_back.entity.UserAuth;
import com.example.ebook_back.service.UserService;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private TokenServiceImpl tokenService;

    private List<UserAuth> userAuthList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController();
        ReflectionTestUtils.setField(userController, "userService", userService);
        ReflectionTestUtils.setField(userController, "tokenService", tokenService);
        // 1. a common user
        User common_user = new User();
        common_user.setId(1);
        common_user.setEmail("common_user@qq.com");
        UserAuth common_userAuth = new UserAuth();
        common_userAuth.setUserId(1);
        common_userAuth.setUserName("existing_user");
        common_userAuth.setPassword("correct_password");
        common_userAuth.setUserMode(0);
        common_userAuth.setBan(false);
        common_userAuth.setUser(common_user);

        // 2. a ban_user
        User ban_user = new User();
        ban_user.setId(2);
        ban_user.setEmail("ban_user@qq.com");
        UserAuth ban_userAuth = new UserAuth();
        ban_userAuth.setUserId(234);
        ban_userAuth.setUserName("ban_user");
        ban_userAuth.setPassword("correct_password");
        ban_userAuth.setUserMode(0);
        ban_userAuth.setBan(true);
        ban_userAuth.setUser(ban_user);

        // 3. a root user
        User root_user = new User();
        root_user.setId(3);
        root_user.setEmail("root_user@qq.com");
        UserAuth root_userAuth = new UserAuth();
        root_userAuth.setUserId(3);
        root_userAuth.setUserName("root");
        root_userAuth.setPassword("correct_password");
        root_userAuth.setUserMode(1);
        root_userAuth.setBan(false);
        root_userAuth.setUser(root_user);

        userAuthList = new ArrayList<>();
        userAuthList.add(common_userAuth);
        userAuthList.add(ban_userAuth);
        userAuthList.add(root_userAuth);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserController-test-data/login.csv", numLinesToSkip = 1)
    void login(String name, String password, boolean result) {
        JSONObject data = new JSONObject();
        data.put("username", name);
        data.put("password", password);

        Mockito.when(userService.findUserByName(name))
                .thenReturn(userAuthList.stream().filter(userAuth -> userAuth.getUserName().equals(name)).findFirst()
                        .orElse(null));
        int status = result ? 1 : -1;
        assertEquals(status, userController.login(data).getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserController-test-data/register.csv", numLinesToSkip = 1)
    void register(String name,String password, String email, boolean result) {
        JSONObject data = new JSONObject();
        data.put("username", name);
        data.put("password", password);
        data.put("mail", email);

        Mockito.when(userService.checkName("existing_user")).thenReturn(false);
        Mockito.when(userService.checkName("new_user")).thenReturn(true);
        Mockito.when(userService.checkMail("existing_user@example.com")).thenReturn(false);
        Mockito.when(userService.checkMail("new_user@example.com")).thenReturn(true);

        Mockito.when(userService.register(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(userService.register(Mockito.eq("crash_case"), Mockito.any(),Mockito.any())).thenReturn(false);

        int status = result ? 1 : -1;
        assertEquals(status, userController.register(data).getStatus());

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserController-test-data/logout.csv", numLinesToSkip = 1)
    void logout(int userId,boolean result) {
        JSONObject data = new JSONObject();
        data.put("userId", userId);

        Mockito.when(userService.logout(123)).thenReturn(true);
        Mockito.when(userService.logout(456)).thenReturn(false);
        int status = result ? 1 : -1;
        assertEquals(status, userController.Logout(data).getStatus());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/UserController-test-data/banuser.csv", numLinesToSkip = 1)
    void banuser(int userId, boolean isBan,boolean result) {
        JSONObject data = new JSONObject();
        data.put("userId", userId);
        data.put("isBan", isBan);

        Mockito.when(userService.banUser(123,true)).thenReturn(true);
        Mockito.when(userService.banUser(456,false)).thenReturn(true);
        Mockito.when(userService.banUser(404,false)).thenReturn(false);
        Mockito.when(userService.banUser(404,true)).thenReturn(false);
        int status = result ? 1 : -1;
        assertEquals(status, userController.banUser(data).getStatus());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserController-test-data/getUserById.csv", numLinesToSkip = 1)
    void getUserById(int userId, boolean result) {
        JSONObject data = new JSONObject();
        data.put("userId", userId);

        Mockito.when(userService.findUserAuthById(123)).thenReturn(userAuthList.get(0));
        Mockito.when(userService.findUserAuthById(456)).thenReturn(userAuthList.get(1));
        Mockito.when(userService.findUserAuthById(404)).thenReturn(null);
        int status = result ? 1 : -1;
        assertEquals(status, userController.getUserById(data).getStatus());
    }
}