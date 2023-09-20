package com.example.ebook_back.websocket;

import com.example.ebook_back.annotation.UserLoginToken;
import com.example.ebook_back.serviceImpl.TokenServiceImpl;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@MessageMapping("/hi")
public class WebSocketServer {

   @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

   @MessageMapping("/sendOrderResponse") //client需发送至/app/sendOrderResponse
    public void sendOrderResponse(Map<String, Object> params) {
        System.out.println("sendOrderResponse");
        simpMessagingTemplate.convertAndSend("/topic", "test connection");
//        simpMessagingTemplate.convertAndSendToUser("1", "/topic/order_response", "test send to User");
    }
}
