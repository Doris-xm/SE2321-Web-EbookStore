package com.example.ebook_back.kafka;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SynchronizedMsg {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private final Map<String, Object> userLocks = new ConcurrentHashMap<>();

    public void sendMsgToUser(String userId, String topic, String msg) {
        // Acquire a lock specific to the userId
        Object userLock = userLocks.computeIfAbsent(userId, key -> new Object());

        // Synchronize on the userLock to ensure sequential access
        synchronized (userLock) {
            simpMessagingTemplate.convertAndSendToUser(userId, topic, msg);
        }
    }

}
