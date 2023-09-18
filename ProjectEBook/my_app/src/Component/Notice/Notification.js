import {Button, Popover} from "antd";
import {useEffect, useState} from "react";

export const Notification = () => {
    const [content, setContent] = useState('暂无通知');
    const text = <span>通知</span>;
    const buttonWidth = 70;
    const [websocket, setWebsocket] = useState(null);

    useEffect(() => {
        if (localStorage.getItem('token') === null || websocket !== null) {
            // setContent('请先登录');
            return;
        }
        try{
            // 创建 WebSocket 连接
            setWebsocket(new WebSocket('ws://localhost:8001/websocket/transfer', [ localStorage.getItem('token')]));

            // 监听 WebSocket 事件
            websocket.onopen = () => {
                console.log('WebSocket connection opened');
            };

            // 监听 WebSocket 事件
            websocket.onmessage = (event) => {
                const receivedMessage = event.data;
                // 处理接收到的消息并触发弹窗显示
                setContent(receivedMessage);
            };

            websocket.onclose = () => {
                console.log('WebSocket connection closed');
            };

            websocket.onerror = (error) => {
                console.error('WebSocket error:', error);
            };
        } catch(e) {
            console.log("websocket err:" + e);
        }

    }, [websocket]);

    return (
        <div
            style={{
                marginLeft: buttonWidth,
                clear: 'both',
                whiteSpace: 'nowrap',
            }}
        >
            <Popover placement="bottom" title={text} content={content} trigger="click">
                <Button>通知</Button>
            </Popover>
        </div>
    )
}