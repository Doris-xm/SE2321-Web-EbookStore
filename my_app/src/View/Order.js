import React from "react";
import { useState } from 'react';
import { booksData } from '../data/book'; // 自定义图书数据
import {Layout, InputNumber, Table, Tag} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import '../css/View.css'

interface DataType {
    name: string;
    price: number;
    num: number;
    total: number;
    addr: string;
    state:string;
}

const cartData = [
    {
        id: 1,
        num: 1,
        addr:'Beijing',
        state:'运输中',
        bought: true,
    },
    {
        id: 2,
        num: 2,
        addr:'Shanghai',
        state:'配送中',
        bought: true,
    },
    {
        id: 3,
        num: 3,
        addr:'Jiangsu',
        state:'已送达',
        bought: true,
    },
];

export class Order extends React.Component {
    state = { cartData };
    render = () => {
        const columns: ColumnsType<DataType> = [
            {
                title: '书名',
                dataIndex: 'name',
                key: 'name',
                render: (text) => <a>{text}</a>,
            },
            {
                title: '价格',
                dataIndex: 'price',
                key: 'price',
            },
            {
                title: '数量',
                dataIndex: 'num',
                key: 'num',
            },
            {
                title: '总价',
                dataIndex: 'total',
                key: 'total',
            },
            {
                title: '地址',
                dataIndex: 'addr',
                key: 'addr',
            },
            {
                title: '状态',
                dataIndex: 'state',
                key: 'state',
            },
        ];
        const combinedData = this.state.cartData.filter((cartItem) => cartItem.bought).map(cartItem => {
            const bookItem = booksData.find(book => book.id === cartItem.id);
            return {
                id: cartItem.id,
                num: cartItem.num,
                addr:cartItem.addr,
                state:cartItem.state,
                name: bookItem.title,
                author: bookItem.author,
                price: bookItem.price,
                cover: bookItem.cover,
                total: bookItem.price * cartItem.num
            };
        });
        return (
            <Layout className={'my-content'}>
                <head1 >My Order</head1>
                <Table columns={columns} dataSource={combinedData} />
            </Layout>
        );
    };
};
export default Order;




