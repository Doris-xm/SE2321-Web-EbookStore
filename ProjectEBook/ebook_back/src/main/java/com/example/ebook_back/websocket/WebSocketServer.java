package com.example.ebook_back.websocket;

import com.example.ebook_back.annotation.UserLoginToken;
import com.example.ebook_back.config.CustomWebSocketConfig;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value="/websocket",configurator = CustomWebSocketConfig.class)
public class WebSocketServer {
    private static final ConcurrentHashMap<String, Session> SESSIONS= new ConcurrentHashMap<>();

    @Autowired
    private TokenServiceImpl tokenService;

//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("Received: " + message);
//    }

    @OnOpen
    @UserLoginToken
    public void onOpen(Session session, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        String userId = String.valueOf(id);
        if(SESSIONS.get(userId) != null) {
            return;
        }
        SESSIONS.put(userId, session);
        System.out.println("New session opened: " + session.getId());
    }

    @OnClose
    @UserLoginToken
    public void onClose(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        int id = tokenService.getUserIdFromToken(token);
        String userId = String.valueOf(id);
        SESSIONS.remove(userId);
        System.out.println("Session closed: " + httpServletRequest.getSession().getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private void sendMessage(Session session, String message) {
        if(session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessageToUser(String userId, String message) {
        System.out.println("sendMessage to user: "+ userId);
        Session toSession = SESSIONS.get(userId);
        sendMessage(toSession, message);
        System.out.println(message);
    }
}
