import {Badge, Button, Popover} from "antd";
import {useEffect, useState} from "react";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
export const Notification = () => {
    const [content, setContent] = useState('暂无通知');
    const [unread, setUnread] = useState(0);
    const [stompClient, setStompClient] = useState(null);
    const [disable, setDisable] = useState(false);
    const text = <span>通知</span>;
    const buttonWidth = 70;
    useEffect(() => {
        // 在组件加载时，建立WebSocket连接
        let token = localStorage.getItem('token');
        if (token == null) {
            setContent('请先登录');
            return;
        }
        if(!disable)
            openSocket(localStorage.getItem('token'));
        else stompClient.send('/app/hi/sendOrderResponse', {}, JSON.stringify({content: 'hello'}));

    }, [disable]);

    const openSocket = (token) => {
        if(disable) return;
        let socket = new SockJS('http://localhost:8001/websocket?token=' + token);
        let stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            setStompClient(stompClient);
            setDisable(true);
            console.log('连接成功');

            // 订阅主题
            stompClient.subscribe('/user/order_response', (msg) => {
                console.log("wwwww",msg);
                setContent(msg);
                setUnread(unread + 1);
                // 在这里处理从WebSocket接收到的消息
            });

            stompClient.subscribe('/topic',(msg) =>{
                console.log("广播：",msg);
            })

            //如果连接断开
            stompClient.onclose = function() {
                console.log('连接断开');
                setDisable(false);
            };
            stompClient.send('/app/hi/sendOrderResponse', {}, JSON.stringify({content: 'hello'}));

        });
    };

    const ClearUnread = () => {
        setUnread(0);
    }

    return (
        <div
            style={{
                marginLeft: buttonWidth,
                clear: 'both',
                whiteSpace: 'nowrap',
            }}
        >
            <Badge count={unread}>
                <Popover placement="bottom" title={text} content={content} trigger="click">
                    <Button onClick={ClearUnread}>
                        通知
                    </Button>
                </Popover>
            </Badge>

        </div>
    )
}