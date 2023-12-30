import React from 'react';
import {Button, Card, Col, DatePicker, Layout, Row, Space, Statistic, Tag} from "antd";
import {banUsers} from "../../Service/UserService";
import {ProList} from "@ant-design/pro-components";
import {getAllOrders, getOrders} from "../../Service/OrderService";
import {getBook, getBooks} from "../../Service/BookService";
export class UserImage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            books: [],
            searchBooks: [],
            total:0, totalPrice:0,
            searchTotal:0, searchTotalPrice:0,
        }
    }
    async componentDidMount() {
        const orders = await getOrders(this.props.user.id);
        const Sales = [];
        let total = 0; let totalPrice = 0;
        orders.map((order) => {
            order.bookOrders.map((item) => {
                if(Sales[item.bookID]) {
                    Sales[item.bookID] += item.quantity;
                } else {
                    Sales[item.bookID] = item.quantity;
                }
            })
        });
        const books = await getBooks();
        const searchBooks = books.filter((book) => {
            return Sales[book.id] !== undefined;
        });
        searchBooks.map((book) => {
            book.quantity = Sales[book.id];
            book.expense = book.quantity * book.bookDetail.price;
            total += book.quantity;
            totalPrice += book.expense;
        });
        searchBooks.sort((a, b) => {
            return b.expense - a.expense;
        });
        this.setState({orders:orders, books: searchBooks, searchBooks: searchBooks
            , total: total, totalPrice: totalPrice, searchTotal: total, searchTotalPrice: totalPrice});
    }
    handleTimePickerChange = (value) => {
        if(value === null) {
            this.setState({searchBooks: this.state.books,
                searchTotal: this.state.total, searchTotalPrice: this.state.totalPrice});
            return;
        }
        const Sales = [];
        this.state.orders.map((order) => {
            order.bookOrders.map((item) => {
                const orderDate = new Date(order.createtime);
                const startDate = new Date(value[0].format('YYYY-MM-DD'));
                const endDate = new Date(value[1].format('YYYY-MM-DD'));
                if(orderDate >= startDate && orderDate <= endDate) {
                    if(Sales[item.bookID]) {
                        Sales[item.bookID] += item.quantity;
                    } else {
                        Sales[item.bookID] = item.quantity;
                    }
                }
            })
        });
        const searchBooks = this.state.books.filter((book) => {
            return Sales[book.id] !== undefined;
        });
        let total = 0; let totalPrice = 0;
        searchBooks.map((book) => {
            book.quantity = Sales[book.id];
            book.expense = book.quantity * book.price;
            total += book.quantity;
            totalPrice += book.expense;
        });
        searchBooks.sort((a, b) => {
            return b.expense - a.expense;
        });
        this.setState({searchBooks: searchBooks, searchTotal: total, searchTotalPrice: totalPrice});
    };

    render =()=> {
        return (
           <Layout style={{backgroundColor:"transparent",display:"flex",alignItems:"center"}}>
               <h1  className="h1">看看你的消费记录吧！</h1>
               <DatePicker.RangePicker  onChange={this.handleTimePickerChange}
                                        style={{width:"50%", margin:"20px"}}
               />
               <Row gutter={16}>
                   <Col span={12} style={{ width: '200px', height: '150px' }}>
                       <Card bordered={false}>
                           <Statistic
                               title="共买书"
                               value={this.state.searchTotal}
                               valueStyle={{
                                   color: '#3f8600',
                               }}
                               suffix={"本"}
                           />
                       </Card>
                   </Col>
                   <Col span={12} style={{ width: '200px', height: '150px' }}>
                       <Card bordered={false}>
                           <Statistic
                               title="共消费"
                               value={this.state.searchTotalPrice}
                               precision={2}
                               valueStyle={{
                                   color: '#cf1322',
                               }}
                               suffix={"元"}
                           />
                       </Card>
                   </Col>
               </Row>
               <ProList
                   style={{margin:"5px", width:"50%"}}
                   search={
                       false
                   }
                   rowKey="name"
                   headerTitle="消费榜"
                   pagination={{
                       pageSize: 8,
                       total: this.state.books.length, // 展示总数
                   }}
                   dataSource={this.state.searchBooks}
                   showActions="hover"
                   metas={{
                       title: {
                           dataIndex: 'title',
                           render: (_, row) => {
                               return (
                                   <Space size={0}>
                                       <Tag color='blue'>{row.bookDetail.title}</Tag>
                                   </Space>
                               );
                           },
                           title: '书名',
                       },
                       description: {
                           title: 'ISBN-13',
                           dataIndex: 'isbn',
                           render: (_, row) => {
                               return (
                                   <span>
                                        <span>  {row.bookDetail.isbn}</span>
                                    </span>
                               );
                           },
                       },
                       avatar: {
                           dataIndex: 'cover',
                           render: (_, row) => {
                               return (
                                   <img src={row.bookDetail.cover} alt={row.bookDetail.title} width={50}/>
                               );
                           },
                           search: false,
                       },
                       subTitle: {
                           render: (_, row) => {
                               return (
                                   <Space size={0}>
                                       <Tag color='green'>总消费：{row.expense}</Tag>
                                   </Space>
                               );
                           },
                           search: false,
                       },
                   }}
               />
           </Layout>
        );
    }
}

export default UserImage;