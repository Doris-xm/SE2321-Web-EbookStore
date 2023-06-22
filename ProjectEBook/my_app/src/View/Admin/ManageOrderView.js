import React from "react";
import {Content,} from "antd/es/layout/layout";
import {Button, Card, DatePicker, Layout, List, Select} from "antd";
import "../../css/View.css"
import Meta from "antd/es/card/Meta";
import {changeOrderState, getAllOrders} from "../../Service/OrderService";
import {ORDER_STATE} from "../../util/Constant";
import Search from "antd/es/input/Search";
import {getBook} from "../../Service/BookService";

export class ManageOrderView extends React.Component {
    constructor(props) {
        super(props);
        this.state = { orders: [],
            searchOrders: [],
            selectedValue: null,
            state: -1,};
    }

    async componentDidMount() {
        const orders = await getAllOrders();
        const newOrders = await Promise.all(
            orders.map(async (order) => {
                const newBookOrders = await Promise.all(
                    order.bookOrders.map(async (bookOrder) => {
                        const book = await getBook(bookOrder.bookID);
                        return { ...bookOrder, title: book.title };
                    })
                );
                return { ...order, bookOrders: newBookOrders };
            })
        );
        this.setState({ orders: newOrders, searchOrders: newOrders });
    }
    onChange = (value) => {
        console.log("ManageOrder::select",value);
        this.setState({state: value})
    };

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
                        this.setState({ searchOrders: updatedOrders });
                    }
                } )
        }
    }
    render = () => {
        const { selectedValue } = this.state;
        return (
            <Layout style={{backgroundColor:'transparent'}}>
                <Content >
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
                            const filteredOrders = this.state.orders.filter((order) => {
                                return order.bookOrders.some((bookOrder) => {
                                    return bookOrder.title.includes(value);
                                });
                            });
                            this.setState({searchOrders: filteredOrders});
                        }}
                    />
                    <DatePicker.RangePicker value={selectedValue} onChange={this.handleTimePickerChange}
                                            style={{width:"50%", margin:"20px"}}
                    />
                    <List
                        grid={{ gutter: 10, column: 4 }}
                        dataSource={this.state.searchOrders}
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
                                                            <p style={{color:"black"}}>书名：{bookOrder.title}</p>
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