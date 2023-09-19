# Websocket

通信双方是对等的（谁都可以主动发消息），突破请求响应的模式。

websocket借用http建立连接，之后对等通信

解决：kafka真正响应无法发回到前端问题

### Intro

与http协议不同，分成两部分

1. **handshake** (HTTP协议)
   1. 发request，用url：ws://
   2. Client 请求中发一个Sec-WebSocket-Key
   3. Server用Sec-WebSocket-Key生成一个Sec-WebSocket-Accept，发回去
   4. Client校验（相同算法），通过则握手
2. **data transfer**

vs http

1. ws是长连接，相当于开了一个session
2. 协议对称

**生命周期**：OnOpen，OnMessage（可重载，以接受不同类型message），OnError，OnClose

发送不是生命周期内的方法

### Encoder &Decoder

消息格式

### Handle err

remove问题session，后端抛异常



>  小结：
>
> 1. kafka监听：有若干次轮询，效率不如ws。比如js在前端监听另一个topic，来表示完成消息
> 2. ws：后端主动向前端发消息，不需要前端多余的请求与试探
