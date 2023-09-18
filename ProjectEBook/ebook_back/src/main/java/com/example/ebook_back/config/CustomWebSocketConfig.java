package com.example.ebook_back.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;

public class CustomWebSocketConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
        // 从请求中获取token
        String token = request.getParameterMap().get("Sec-WebSocket-Protocol").get(0);
        // 设置Sec-WebSocket-Protocol头部
        response.getHeaders().put("Sec-WebSocket-Protocol", Collections.singletonList(token));
    }
}
