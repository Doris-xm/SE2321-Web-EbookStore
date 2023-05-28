import React from "react";
import {Content,} from "antd/es/layout/layout";
import {Button, Card, Layout, List, Select} from "antd";
import "../../css/View.css"
import Meta from "antd/es/card/Meta";
import {changeOrderState, getAllOrders} from "../../Service/OrderService";
import {ORDER_STATE} from "../../util/Constant";

export class ManageOrderView extends React.Component {
    constructor(props) {
        super(props);
        this.state = { orders: [] };
        this.state = { state: -1 };
    }
    async componentDidMount() {
        const orders = await getAllOrders();
        this.setState({ orders });
    }
    onChange = (value) => {
        console.log("ManageOrder::select",value);
        this.setState({state: value})
    };
    onSearch = (value) => {
        console.log('search:', value);
    };
    submit = async (orderId) => {
        console.log("ManageOrder::submit",orderId,this.state.state)
        if(this.state.state > 0) {
            await changeOrderState(orderId,this.state.state).then(
                (res) => {
                    console.log("ManageOrder::submit",res)
                    if(res) {
                        const updatedOrders = this.state.orders.map((order) => {
                            if (order.orderID === orderId) {
                                return { ...order, state: this.state.state };
                            }
                            return order;
                        });
                        console.log("ManageOrder::submit",updatedOrders)
                        this.setState({ orders: updatedOrders });
                    }
                } )
        }
    }
    render = () => {
        return (
            <Layout style={{backgroundColor:'transparent'}}>
                <Content >
                    <List
                        grid={{ gutter: 10, column: 4 }}
                        dataSource={this.state.orders}
                        pagination={{
                            onChange: page => {
                                console.log(page);
                            },
                            pageSize: 16,
                        }}
                        renderItem={item => (
                            <List.Item>
                                <Card
                                    style={{width: '300px',margin:'6px'}}
                                    hoverable
                                    // onClick={this.showBookDetails.bind(this, info.id)}
                                >
                                    <Meta
                                        title={`用户: ${item.userID}  订单号: ${item.orderID}`}
                                        description={
                                            <>
                                                <div>
                                                    {item.bookOrders.map( (bookOrder) => (
                                                        <div style={{display:"flex",flexDirection:"row",verticalAlign:"text-top", justifyContent:"space-between",width:"100%"}}>
                                                            <a href={`/bookDetails/${bookOrder.bookID}`} style={{marginLeft: "10px"}}>
                                                                书籍编号：{bookOrder.id}
                                                            </a>
                                                            <p style={{color:"grey"}}>{bookOrder.quantity}本</p>
                                                            <p style={{color:"grey"}}>{parseFloat(bookOrder.totalprice).toFixed(2)}元</p>

                                                        </div>
                                                    ))}
                                                </div>

                                            </>
                                        }
                                    />
                                    <br/>
                                    <div>收件人：{item.receiver}</div>
                                    <div>电话：{item.phone}</div>
                                    <div>地址：{item.address}</div>
                                    <div>下单时间：{item.createtime}</div>
                                    <br/>
                                    <div>总价：{`¥ ${item.totalprice}`}</div>

                                    <br/>
                                    <div style={{ display: 'flex', flexDirection: 'row' , justifyContent: 'space-between'}}>
                                    <div>{item.state ===0 &&  `状态：${ORDER_STATE[item.state]}`}</div>
                                    <div>{item.state &&  `状态：已${ORDER_STATE[item.state]}`}</div>
                                    <Select
                                        showSearch
                                        placeholder="Select a person"
                                        optionFilterProp="children"
                                        onChange={this.onChange}
                                        onSearch={this.onSearch}
                                        defaultValue={`已${ORDER_STATE[item.state]}`}
                                        filterOption={(input, option) =>
                                            (option?.label ?? '').toLowerCase().includes(input.toLowerCase())
                                        }
                                        options={[
                                            {
                                                value: '0',
                                                label: `${ORDER_STATE[0]}`,
                                            },
                                            {
                                                value: '1',
                                                label: `已${ORDER_STATE[1]}`,
                                            },
                                            {
                                                value: '2',
                                                label: `已${ORDER_STATE[2]}`,
                                            },
                                            {
                                                value: '3',
                                                label: `已${ORDER_STATE[3]}`,
                                            },
                                            {
                                                value: '4',
                                                label: `已${ORDER_STATE[4]}`,
                                            },
                                            {
                                                value: '5',
                                                label: `已${ORDER_STATE[5]}`,
                                            }
                                        ]}
                                    />
                                    </div>
                                    <div style={{marginTop:'5px',textAlign:'right'}}>
                                        <Button onClick={this.submit.bind(this, item.orderID)} type="default" style={{width:"50%"}}>确认修改</Button>
                                    </div>

                                </Card>
                            </List.Item>
                        )}
                    />
                </Content>
            </Layout>
        );

    };
};
export default ManageOrderView;