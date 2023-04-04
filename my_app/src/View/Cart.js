import React from "react";
import { useState } from 'react';
import {ChangeQuantity} from "../Service/BookService";
// import {cartData} from "../data/cart";
import {Layout, InputNumber, Table, Tag, Checkbox, Button} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import '../css/View.css'
import {Link} from "react-router-dom";

interface DataType {
    bookID: number;
    title: string;
    author: string;
    price: number;
    num: number;
    total: number;
    selected: boolean; // 新增加的选择框字段
}



export class Cart extends React.Component {
    state = {
        cartData: [],
    };
    handleNumChange= (ID:number, value: number) => {
        // console.log('handleNumChange', ID, value);
        ChangeQuantity(ID,value);
            // 更新购物车状态
            this.setState({ cartData: this.getCartData() });
    }

    handleSelectChange= (id: number) =>  {
        const updatedCartData = this.state.cartData.map((item) => {
            if (item.id === id) {
                return { ...item, selected: !item.selected };
            } else {
                // 第二次点击
                return item;
            }
        });
        // 更新购物车状态
        this.setState({ cartData: updatedCartData });
    }

    getCartData = () => {
        // 从 localStorage 中获取购物车数据
        let cart = localStorage.getItem("cart");
        if (cart == null) {
            localStorage.setItem("cart", "[]");
            return [];
        } else {
            cart = JSON.parse(cart);
        }
        console.log('购物车数组：', cart)
        const cartItems = cart.map((book) => {
            return {
                bookID: book.id,
                title: book.title, // 或 book.name，根据实际属性名称
                author: book.author,
                price: book.price,
                num: book.quantity,
                total: book.price * book.quantity,
                selected: book.selected || false, // 默认未选中x
            };
        });
        cartItems.forEach((item) => {
            // console.log(item);
            if (item.num === 0) {
                console.log(item);
                // 删除
                const updatedCartData = cartItems.filter((it) => it.id !== item.id);
                if (cartItems.length === 1) {
                    localStorage.setItem("cart", "[]");
                    return [];
                }
                return cartItems;
            }
        });
        return cartItems;
    };


    render = () => {

        const columns: ColumnsType<DataType> = [
            {
                title: '',
                dataIndex: 'id',
                key: 'id',
                render: (id, record) => (
                    <Checkbox
                        checked={record.selected}// 根据 selected 字段确定当前复选框是否选中
                        onChange={() => this.handleSelectChange([record.id]) }
                       />
                ),
            },
            {
                title: '书名',
                dataIndex: 'title',
                key: 'title',
                render: (text) => <a>{text}</a>,
            },
            {
                title: '作者',
                dataIndex: 'author',
                key: 'author'
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
                render: (text, record) => (
                    <InputNumber
                        min={0}
                        defaultValue={text}
                        onChange={(value) => this.handleNumChange(record.bookID, value)}
                    />
                ),
            },
            {
                title: '总价',
                dataIndex: 'total',
                key: 'total',
            },
        ];
       
        return (
            <Layout className={'my-content'}>
                <h1 >My Cart</h1>
                <Checkbox.Group
                    // value={this.state.cartData.filter(item => item.selected).map(item => item.id)}
                    onChange={(checkedValue) => this.handleSelectChange(checkedValue)}>
                    <div className={'my-content'}>
                        <Table rowKey={record => record.id}
                            columns={columns} dataSource={this.getCartData()} />
                    </div>

                </Checkbox.Group>
                <div>
                    <Button className="buttons"  >
                        <Link to="http://www.alipay.com/" target="_blank">付款</Link>
                    </Button>
                </div>

            </Layout>
        );
    };
};
export default Cart;




