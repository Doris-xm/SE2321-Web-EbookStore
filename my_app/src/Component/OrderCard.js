import { Card } from 'antd';
import React, { useState } from 'react';
import {OrderStateBar} from './OrderStateBar';

interface OrderType {
    orderID: number;
    userID: number;
    state: number;
    address: string;
    totalPrice: number;
    createTime: Date;
    finishTime: Date;
    comment?: string;
}



const tabList = [
    {
        key: 'detail',
        tab: '订单详情',
    },
    {
        key: 'state',
        tab: '订单状态',
    },
];

// const contentList: Record<string, React.ReactNode> = {
//     detail: <p>content1</p>,
//     state: <OrderStateBar step={2}/>,
// };
const contentList = (order: OrderType): Record<string, React.ReactNode> => {
    if (!order) return {};
    return {
        detail: (
            <div>
                {/*<p>订单号: {order.orderID}</p>*/}
                <p>价格: {order.totalPrice}</p>
                <p>地址: {order.address}</p>
                <p>创建时间: {order.createTime}</p>
                <p>完成时间: {order.finishTime}</p>
                <p>评价: {order.comment}</p>
            </div>
        ),
        state: <OrderStateBar step={order.state} />,
    };
};

export const OrderCard: React.FC<{orders:OrderType}> = ({orders}) => {
    const [activeTabKey1, setActiveTabKey1] = useState('detail');
    const onTab1Change = (key: string) => {
        setActiveTabKey1(key);
    };
    return (

        <>
            <br />
            <Card
                style={{ width: '97%' }}
                title={`订单号： ${orders.orderID}`}
                // extra={<a href="#">More</a>}
                tabList={tabList}
                activeTabKey={activeTabKey1}
                onTabChange={onTab1Change}
            >
                {contentList(orders)[activeTabKey1]}
            </Card>

            <br />
        </>
    );
};

export default OrderCard;