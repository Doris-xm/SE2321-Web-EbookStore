import React, {useState} from "react";
import {ChangeQuantity, getCart} from "../../Service/CartService";
import {Layout, InputNumber, Table, Button} from 'antd';
import type { TableRowSelection } from 'antd/es/table/interface';
import type { ColumnsType } from 'antd/es/table';
import '../../css/View.css';
import OrderModel from "../../Component/OrderModel";
import {checkSession, getUser} from "../../Service/UserService";
import {Link} from "react-router-dom";
import {getBook} from "../../Service/BookService";

interface DataType {
    bookID: number;
    title: string;
    author: string;
    price: number;
    quantity: number;
    total: number;
    selected: boolean; // 新增加的选择框字段
}


export class Cart extends React.Component {
    state = {
        cartData: [],
        user:null,
    };
    async componentDidMount() {
        const user = await getUser();
        if(user === null) {
            this.setState({ user });
        }
        else {
           await checkSession(user.id).then(async (res) => {
                if(res === false) {
                    this.setState({ user: null });
                }
                else {
                    this.setState({ user });
                    await this.getCartData();
                }
            });
        }

        // await this.getCartData();
        // const cartData = await this.getCartData();
        // console.log('cartData', cartData)
        // this.setState({ cartData });
    }
    handleNumChange= async (ID:number, value: number) => {
        //更新前端的state（改变后显示的不是从后端发来的，而是前端直接改的，避免频繁刷新）
        let newData = this.state.cartData;
        const existingBookIndex = newData.findIndex((item) => item.bookID === ID);
        // 如果书籍已经存在，则更新其数量
        if (existingBookIndex === -1) {
           return;
        } else {
            if(value > 0){
                newData[existingBookIndex].quantity = value;
                // let book = await getBook(ID);
                newData[existingBookIndex].total = parseFloat((newData[existingBookIndex].price * value).toFixed(2));
            }
            else {
                //react默认都是浅监听，只会监听数据的第一层，内层数据发生改变，并不会监听到。
                //先将原数组浅拷贝，赋值给新数组，再修改新数组（不影响原状态），将修改后的新数组使用setState传递进去，这样就会引起视图更新。
                newData = [...this.state.cartData];
                newData.splice(existingBookIndex,1);
                console.log("cart",newData);
            }
        }
        this.setState({ cartData: newData });

        // 更新数据库
        await ChangeQuantity(ID,value);
    }

    getCartData = async () => {
        let cart = null;
        cart = await getCart();
        const cartItemsPromise = cart.map(async (cart_book) => {
            let book = await getBook(cart_book.bookID);
            return {
                bookID: cart_book.bookID,
                title: book.title,
                author: book.author,
                price: book.price,
                quantity: cart_book.quantity,
                total:  parseFloat((book.price * cart_book.quantity).toFixed(2)),
                // selected: cart_book.selected || false, // 默认未选中x
                selected: true, // 默认未选中x
            };
        });

        Promise.all(cartItemsPromise)
            .then((cartItems) => {
                console.log('购物车数组：', cartItems)
                this.setState({ cartData: cartItems });
                return cartItems;
            });
        // return null;
    };

    handleClearCart = () => {
        // 更新购物车数据为空
        this.setState({ cartData: [] });
    };

    render = () => {
        if(this.state.user === null) {
            return (
                <div  className={"notLogin-content"} style={{ margin:'center'}}>
                    请先登录
                    <br/>
                    <Button className="buttons" >
                        <Link to = '/login'>
                            登录
                        </Link>
                    </Button>
                </div>
            );
        }
        const columns: ColumnsType<DataType> = [
            {
                title: '书名',
                dataIndex: 'title',
                key: 'title',
                render: (text,record) =>
                    <a href={`/bookDetails/${record.bookID}`}>
                        {text}
                    </a>,
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
                dataIndex: 'quantity',
                key: 'quantity',
                render: (text, record) => (
                    <InputNumber
                        min={0}
                        defaultValue={text}
                        onChange={async (value) => {
                            await this.handleNumChange(record.bookID, value);
                        }}

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
                    columns={columns} dataSource={this.state.cartData}  />

                <div>
                    <OrderModel cartData = {this.state.cartData} onClearCart={this.handleClearCart}/>
                </div>

            </Layout>
        );
    };
};
export default Cart;




