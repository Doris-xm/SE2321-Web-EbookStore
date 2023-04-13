import React from 'react';
import { Steps } from 'antd';

const description = 'This is a description.';
export const OrderStateBar: React.FC = (step) => (
    console.log("step",step),
    <Steps
        direction="vertical"
        size="small"
        current={step}
        items={[
            { title: 'Finished', description },
            {
                title: 'In Progress',
                description,
            },
            {
                title: 'Waiting',
                description,
            },
        ]}
    />
);

export default OrderStateBar;