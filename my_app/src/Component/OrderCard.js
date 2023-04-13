import { Card } from 'antd';
import React, { useState } from 'react';
import {OrderStateBar} from './OrderStateBar';

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

const contentList: Record<string, React.ReactNode> = {
    detail: <p>content1</p>,
    state: <OrderStateBar step={2}/>,
};


export const OrderCard: React.FC = () => {
    const [activeTabKey1, setActiveTabKey1] = useState('detail');
    const onTab1Change = (key: string) => {
        setActiveTabKey1(key);
    };
    return (
        <>
            <Card
                style={{ width: '100%' }}
                title="Card title"
                extra={<a href="#">More</a>}
                tabList={tabList}
                activeTabKey={activeTabKey1}
                onTabChange={onTab1Change}
            >
                {contentList[activeTabKey1]}
            </Card>
            <br />
            <br />
        </>
    );
};

export default OrderCard;