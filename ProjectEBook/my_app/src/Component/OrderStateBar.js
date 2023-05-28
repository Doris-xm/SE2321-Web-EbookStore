import React from 'react';
import { Steps } from 'antd';
import {ORDER_STATE} from "../util/Constant";

const description1 = "已付款，商家尚未发货";
const description2 = "商品已发货！";
const description3 = "商品已送达，记得签收";
const description4 = "您已收货，记得评价";
const description5 = "订单已评价，交易完成";


export const OrderStateBar: React.FC<{step: number}> = ({step}) => {
    const items = [
        { title: ORDER_STATE["1"], description:description1, status: 'wait' },
        { title: ORDER_STATE["2"],description:description2, status: 'wait' },
        { title: ORDER_STATE["3"],description:description3, status: 'wait' },
        { title: ORDER_STATE["4"],description: description4, status: 'wait' },
        { title: ORDER_STATE["5"], description:description5, status: 'wait' },
    ];

    // 根据当前步骤更新每个状态的状态
    items.forEach((item, index) => {
        if (step > index) {
            item.status = 'finish';
        } else if (step === index) {
            item.status = 'process';
        } else {
            item.status = 'wait';
        }
    });

    // 根据状态返回对应的标题
    const getTitle = (title: string, status: string) => {
        return status === 'finish' ?  `已${title}` : `未${title}` ;
    };

    // 根据每个状态项生成 Steps 组件的 Step 组件数组
    const steps = items.map((item, index) => {
        return (
            <Steps key={index} status={item.status} title={getTitle(item.title, item.status)} description={item.description} />
        );
    });

    return (
        <Steps direction="vertical" size="small" current={step}>
            {steps}
        </Steps>
    );
};


export default OrderStateBar;