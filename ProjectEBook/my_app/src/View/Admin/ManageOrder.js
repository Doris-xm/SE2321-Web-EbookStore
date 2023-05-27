import React from "react";
import {Content,} from "antd/es/layout/layout";
import {Card, Layout, List} from "antd";
import "../css/View.css"
import Meta from "antd/es/card/Meta";
import {getAllOrders} from "../../Service/OrderService";

export class ManageOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = { orders: [] };
    }
    async componentDidMount() {
        const orders = await getAllOrders();
        this.setState({ orders });
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
                                    className={'book-card'}
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
                                                                {bookOrder.bookName}
                                                            </a>
                                                            <p style={{color:"grey"}}>{bookOrder.quantity}本</p>
                                                            <p style={{color:"grey"}}>{parseFloat(bookOrder.totalprice).toFixed(2)}元</p>

                                                        </div>
                                                    ))}
                                                </div>
                                                <div>{item.author}</div>
                                                <div>{` ¥ ${item.price}`}</div>
                                            </>
                                        }
                                    />
                                </Card>
                            </List.Item>
                        )}
                    />
                </Content>
            </Layout>
        );

    };
};
export default ManageOrder;