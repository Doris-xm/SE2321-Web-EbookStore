import React from "react";
import {Layout, InputNumber, Table, Tag, List, Button} from 'antd';
import '../css/View.css'
import {getOrders} from "../Service/OrderService";
import {OrderCard} from '../Component/OrderCard';
import {getUser} from "../Service/UserService";
import {Link} from "react-router-dom";
// interface DataType {
//     orderID: number;
//     title: string;
//     price: number;
//     quantity: number;
//     total: number;
//     address:string;
//     state:string;
// }

export class Order extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            user:null,
        };
    }
    async componentDidMount() {
        const user = await getUser();
        this.setState({ user });

        if(user===null)
            return;

        const orders = await getOrders(user.id);
        this.setState({ orders });
    }

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
                            <OrderCard orders={item}  />
                        </List.Item>
                    )}
                />
            </Layout>
        );
    };
};
export default Order;
