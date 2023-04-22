import React from 'react';
import { Steps } from 'antd';

const description = 'This is a description.';

export const OrderStateBar: React.FC<{step: number}> = ({step}) => {
    const items = [
        { title: 'Paid', description, status: 'wait' },
        { title: 'Sent', description, status: 'wait' },
        { title: 'Reached', description, status: 'wait' },
        { title: 'Signed', description, status: 'wait' },
        { title: 'Commented', description, status: 'wait' },
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
        return status === 'finish' ? title : `Not ${title}` ;
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