package com.example.ebook_back.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketServer {
    private static final ConcurrentHashMap<String, Session> SESSIONS= new ConcurrentHashMap<>();

//    @OnMessage
//    public void onMessage(String message, Session session) {
//        System.out.println("Received: " + message);
//    }

    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.put(session.getId(), session);
        System.out.println("New session opened: " + session.getId());
    }
}
