import React from "react";
import {Layout, InputNumber, Table, Tag, List, Button, DatePicker, Space} from 'antd';
import '../../css/View.css'
import {getOrders} from "../../Service/OrderService";
import {OrderCard} from '../../Component/Order/OrderCard';
import {getUser} from "../../Service/UserService";
import {Link} from "react-router-dom";
import Search from "antd/es/input/Search";
import {getBook} from "../../Service/BookService";

export class Order extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            searchOrders: [],
            user:null,
            selectedValue: null
        };
    }
    handleTimePickerChange = (value) => {
        this.setState({ selectedValue: value });
        if(value === null) {
            this.setState({searchOrders: this.state.orders});
            return;
        }
        const searchOrders = this.state.orders.filter((order) => {
            const orderDate = new Date(order.createtime);
            const startDate = new Date(value[0].format('YYYY-MM-DD'));
            const endDate = new Date(value[1].format('YYYY-MM-DD'));
            return orderDate >= startDate && orderDate <= endDate;
        });
        console.log(searchOrders);
        this.setState({searchOrders: searchOrders});
    };
    async componentDidMount() {
        const user = await getUser();
        this.setState({ user });

        if(user===null)
            return;

        const orders = await getOrders(user.id);
        this.setState({ orders, searchOrders: orders });
    }

    render = () => {
        const { selectedValue } = this.state;
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
        return (
            <Layout className={'order-content'}>
                    <Search
                        placeholder="输入书名"
                        allowClear
                        enterButton="搜索"
                        size="large"
                        style={{width:"75%", margin:"20px"}}
                        onSearch={ async (value) => {
                            if (value === "") {
                                this.setState({searchOrders: this.state.orders});
                                return;
                            }
                            const searchOrders = await Promise.all(
                                this.state.orders.map(async (order) => {
                                    const filteredBooks = await Promise.all(
                                        order.bookOrders.map(async (book) => {
                                            const Book_name = await getBook(book.bookID);
                                            return Book_name.bookDetail.title.includes(value);
                                        })
                                    );
                                    return filteredBooks.includes(true) ? order : null;
                                }));
                            const filteredOrders = searchOrders.filter((order) => order !== null);
                            this.setState({searchOrders: filteredOrders});
                        }}
                    />
                    <DatePicker.RangePicker value={selectedValue} onChange={this.handleTimePickerChange}
                                            style={{width:"50%", margin:"20px"}}
                    />
                <List
                    grid={{ gutter: 10, column: 3 }}
                    dataSource={this.state.searchOrders}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 9,
                    }}

                    renderItem={item => (
                        <List.Item>
                            <OrderCard orders={item}  />
                        </List.Item>
                    )}
                />
            </Layout>
        );
    };
};
export default Order;
