import React, {useState} from "react";
import {ChangeQuantity} from "../Service/OrderService";
import {Layout, InputNumber, Table} from 'antd';
import type { TableRowSelection } from 'antd/es/table/interface';
import type { ColumnsType } from 'antd/es/table';
import '../css/View.css';
import OrderModel from "../Component/OrderModel";

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
                total:  parseFloat((book.price * book.quantity).toFixed(2)),
                selected: book.selected || false, // 默认未选中x
            };
        });
        const updatedCartItems = cartItems.filter((item) => item.num !== 0);

        return updatedCartItems;
    };



    render = () => {

        const columns: ColumnsType<DataType> = [
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

        // const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);
        // const onSelectChange = (newSelectedRowKeys: React.Key[]) => {
        //     console.log('selectedRowKeys changed: ', newSelectedRowKeys);
        //     setSelectedRowKeys(newSelectedRowKeys);
        // };
        // const onSelectChange = (newSelectedRowKeys: React.Key[]) => {
        //     console.log('selectedRowKeys changed: ', newSelectedRowKeys);
        //     setSelectedRowKeys(newSelectedRowKeys);
        // };
        // const rowSelection: TableRowSelection<DataType> = {
        //     selectedRowKeys,
        //     onChange: onSelectChange,
        //     selections: [
        //         Table.SELECTION_ALL,
        //         Table.SELECTION_INVERT,
        //         Table.SELECTION_NONE,
        //     ],
        //     getCheckboxProps: (record: DataType) => ({
        //         disabled: !record.selected,
        //     }),
        // };

        return (
            <Layout className={'my-content'}>
                <h1 >My Cart</h1>
                <Table rowKey={record => record.id} style={{width: '95%', backgroundColor: 'transparent'}}
                    columns={columns} dataSource={this.getCartData()}  />

                <div>
                    <OrderModel />
                </div>

            </Layout>
        );
    };
};
export default Cart;




