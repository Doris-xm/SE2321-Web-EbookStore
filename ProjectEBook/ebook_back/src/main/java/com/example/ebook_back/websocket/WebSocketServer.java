package com.example.ebook_back.websocket;

import com.example.ebook_back.annotation.UserLoginToken;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value="/websocket/{token}")
public class WebSocketServer {

    @Resource
    private TokenServiceImpl tokenServiceImpl;

    private static final ConcurrentHashMap<String, Session> SESSIONS= new ConcurrentHashMap<>();

    private static WebSocketServer webSocketServer;

//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("Received: " + message);
//    }
    @PostConstruct
    public void init() {
        /* fix Component中Autowired注入为null问题 */
        webSocketServer = this;
        webSocketServer.tokenServiceImpl = this.tokenServiceImpl;
    }

    @OnOpen
    @UserLoginToken
    public void onOpen(Session session, @PathParam("token") String token) {
        int id = webSocketServer.tokenServiceImpl.getUserIdFromToken(token);
        String userId = String.valueOf(id);
        if(SESSIONS.get(userId) != null) {
            return;
        }
        SESSIONS.put(userId, session);
        System.out.println("New session opened: " + session.getId());
    }

    @OnClose
//    @UserLoginToken
    public void onClose() {
        System.out.println("Session closed");
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
