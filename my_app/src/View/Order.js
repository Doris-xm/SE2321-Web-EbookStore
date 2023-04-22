import React from "react";
import { useState } from 'react';
import { booksData } from '../data/book'; // 自定义图书数据
import {Layout, InputNumber, Table, Tag, List} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import '../css/View.css'
import {getOrders, OrderStateEnum} from "../Service/OrderService";
import {OrderCard} from '../Component/OrderCard';
import {BookCard} from "../Component/BookCard";
interface DataType {
    orderID: number;
    title: string;
    price: number;
    quantity: number;
    total: number;
    address:string;
    state:string;
}

export class Order extends React.Component {
    constructor(props) {
        super(props);
        this.state = { orders: [] };
    }
    async componentDidMount() {
        const orders = await getOrders(this.props.user.id);
        this.setState({ orders });
    }
    render = () => {

        return (

            <Layout className={'order-content'}>
                <head1 >My Order</head1>
                <List
                    grid={{ gutter: 10, column: 3 }}
                    dataSource={this.state.orders}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 16,
                    }}

                    renderItem={item => (
                        <List.Item>
                            <OrderCard orders={item} />
                        </List.Item>
                    )}
                />
            </Layout>
        );
    };
};
export default Order;





// //Original Code

// import React from "react";
// import { useState } from 'react';
// import { booksData } from '../data/book'; // 自定义图书数据
// import {Layout, InputNumber, Table, Tag} from 'antd';
// import type { ColumnsType } from 'antd/es/table';
// import '../css/View.css'
// import {getBooks} from "../Service/BookService";
// import {getOrders, OrderStateEnum} from "../Service/OrderService";
// import {OrderCard} from '../Component/OrderCard';
// interface DataType {
//     id: number;
//     title: string;
//     price: number;
//     quantity: number;
//     total: number;
//     address:string;
//     state:string;
// }
//
// export class Order extends React.Component {
//     // state = { cartData };
//     constructor(props) {
//         super(props);
//         this.state = { orders: [] };
//     }
//     async componentDidMount() {
//         const orders = await getOrders();
//         this.setState({ orders });
//     }
//     render = () => {
//         const columns: ColumnsType<DataType> = [
//             {
//                 title: '书名',
//                 dataIndex: 'title',
//                 key: 'title',
//                 render: (text) => <a>{text}</a>,
//             },
//             {
//                 title: '价格',
//                 dataIndex: 'price',
//                 key: 'price',
//             },
//             {
//                 title: '数量',
//                 dataIndex: 'quantity',
//                 key: 'quantity',
//             },
//             {
//                 title: '总价',
//                 dataIndex: 'total',
//                 key: 'total',
//             },
//             {
//                 title: '地址',
//                 dataIndex: 'address',
//                 key: 'address',
//             },
//             {
//                 title: '状态',
//                 dataIndex: 'state',
//                 key: 'state',
//             },
//         ];
//         const orderItems = this.state.orders.map((order) => {
//             return {
//                 bookID: order.id,
//                 title: order.title,
//                 price: order.price,
//                 quantity: order.quantity,
//                 total:  parseFloat((order.price * order.quantity).toFixed(2)),
//                 address: order.address,
//                 orderState:  order.orderState,
//             };
//         });
//         return (
//             <Layout className={'my-content'}>
//                 <head1 >My Order</head1>
//                 <Table columns={columns} dataSource={orderItems} style={{ width: '90%' }}/>
//                 <OrderCard />
//             </Layout>
//         );
//     };
// };
// export default Order;
//
//
//
//
