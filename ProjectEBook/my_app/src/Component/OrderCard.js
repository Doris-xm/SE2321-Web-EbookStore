import {Card, Table} from 'antd';
import React, {useEffect, useState} from 'react';
import {OrderStateBar} from './OrderStateBar';
import {getBook} from "../Service/BookService";
// import {Link} from "react-router-dom";



// interface OrderType {
//     orderID: number;
//     userID: number;
//     state: number;
//     address: string;
//     totalprice: number;
//     createtime: Date;
//     finishtime: Date;
//     comment?: string;
// }



const tabList = [
    {
        key: 'detail',
        tab: '订单详情',
    },
    {
        key: 'steps',
        tab: '订单状态',
    },
];
const ContentList: React.FC = ({order}) => {

    console.log("OrderCard",order);
    const [bookOrdersNew, setBookOrdersNew] = useState([]);

    /**
     * 根据后端传来的每个order中的bookorder，只包含bookID
     * 通过bookID异步获取book（getBook）
     * @param bookID
     */
    useEffect(() => {
        const fetchBookOrders = async () => {
            const bookOrdersNew = await Promise.all(
                order.bookOrders.map(async (bookOrder) => {
                    const book = await getBook(bookOrder.bookID);
                    return {
                        bookName: book.title,
                        bookID: bookOrder.bookID,
                        quantity: bookOrder.quantity,
                        totalprice: bookOrder.totalprice,
                    };
                })
            );
            setBookOrdersNew(bookOrdersNew);
        };
        fetchBookOrders();
    }, [order]);
    if (!order) return {};
    return {

        detail: (

            <div>
                {bookOrdersNew.map( (bookOrder) => (
                    <div style={{display:"flex",flexDirection:"row",verticalAlign:"text-top", justifyContent:"space-between",width:"100%"}}>
                        <a href={`/bookDetails/${bookOrder.bookID}`} style={{marginLeft: "10px"}}>
                            {bookOrder.bookName}
                        </a>
                        <p style={{color:"grey"}}>{bookOrder.quantity}本</p>
                        <p style={{color:"grey"}}>{parseFloat(bookOrder.totalprice).toFixed(2)}元</p>

                    </div>
                ))}
                <p>地址: {order.address}</p>
                <p>创建时间: {order.createtime}</p>
                <p>完成时间: {order.finishtime}</p>
                <p>评价: {order.comment}</p>


                <div style={{ textAlign: "right", marginTop: 10 }}>
                    <p>总价：{parseFloat(order.totalprice).toFixed(2)}元</p>
                </div>
            </div>
        ),
        steps: <OrderStateBar step={order.state} />,
    };
};

export const OrderCard: React.FC = ({orders}) => {
    const [activeTabKey1, setActiveTabKey1] = useState('detail');
    const onTab1Change = (key: string) => {
        setActiveTabKey1(key);
    };
    return (

        <>
            <br />
            <Card
                style={{ width: '97%' }}
                title={`订单号： ${orders.orderID}`}
                // extra={<a href="#">More</a>}
                tabList={tabList}
                activeTabKey={activeTabKey1}
                onTabChange={onTab1Change}
            >
                {ContentList({ order: orders })[activeTabKey1]}
            </Card>

            <br />
        </>
    );
};

export default OrderCard;