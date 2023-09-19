package com.example.ebook_back.config;

import com.example.ebook_back.annotation.UserLoginToken;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;


public class UserHandleShake extends DefaultHandshakeHandler {
    @Autowired
    private TokenServiceImpl tokenServiceImpl;
    @Override
    @UserLoginToken
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        System.out.println("shake");
        String token = servletRequest.getParameter("token");
        int id = tokenServiceImpl.getUserIdFromToken(token);
        UserInfoPri info = new UserInfoPri();
        info.setUserName(String.valueOf(id));
        return info;
    }
}
