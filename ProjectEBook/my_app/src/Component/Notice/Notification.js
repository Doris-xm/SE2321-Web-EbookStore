import {Badge, Button, Popover} from "antd";
import {useEffect, useState} from "react";
export const Notification = () => {
    const [content, setContent] = useState('暂无通知');
    const [unread, setUnread] = useState(0);
    const [connect, setConnect] = useState(false);
    const text = <span>通知</span>;
    const buttonWidth = 70;
    useEffect(() => {
        let token = localStorage.getItem('token');
        if(token === null){
            setContent('请先登录')
            return;
        }
        if(connect){
            return;
        }
        const websocket = new WebSocket('ws://localhost:8001/websocket/' + token);
        websocket.onopen = () => {
            console.log('WebSocket connection opened');
            setConnect(true)
        }
        websocket.onmessage = (event) => {
            console.log('wwwwwweeeeeebbbbbb:');
            const receivedMessage = event.data;
            console.log(receivedMessage);
            console.log(event)
            // 处理接收到的消息并触发弹窗显示
            if(unread === 0)
                setContent(receivedMessage);
            else {
                setContent(content + '\n' + receivedMessage);
            }
            setUnread(unread + 1)
        }
        websocket.onclose = () => {
            console.log('WebSocket connection closed');
            setConnect(false)
        }
        websocket.onerror = () => {
            console.log('WebSocket connection error');
            setConnect(false)
        }
    }, [content])

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