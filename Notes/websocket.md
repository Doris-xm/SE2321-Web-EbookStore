# Websocket

通信双方是对等的（谁都可以主动发消息），突破请求响应的模式。

websocket借用http建立连接，之后对等通信

解决：kafka真正响应无法发回到前端问题

### Intro

与http协议不同，分成两部分

1. handshake
   1. 发request，用url：ws://
   2. Client 请求中发一个Sec-WebSocket-Key
   3. Server用Sec-WebSocket-Key生成一个Sec-WebSocket-Accept，发回去
   4. Client校验，通过则握手
2. data transfer

vs http

1. ws是长连接，相当于开了一个session
2. 协议对称



### Encoder &Decoder



### Handle err

remove问题session，后端抛异常



