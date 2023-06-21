import React from "react";
import {getBooks} from "../../Service/BookService";
import {DatePicker, Layout, Space, Tag} from "antd";
import Search from "antd/es/input/Search";
import {ProList} from "@ant-design/pro-components";
import {Link} from "react-router-dom";
import {getAllItems, getAllOrders} from "../../Service/OrderService";


export class SalesView extends React.Component {
    constructor(props) {
        super(props);
        this.state = { orders: [], books: [],
            searchBooks: [],
            originBooks: [],
        };
    }
    async componentDidMount() {
        const orders = await getAllOrders();
        const Sales = [];
        orders.map((orderitems) => {
            orderitems.bookOrders.map((order) => {
                if(Sales[order.bookID])
                    Sales[order.bookID] += order.quantity;
                else
                    Sales[order.bookID] = order.quantity;
            });
        });
        const books = await getBooks();
        const searchBooks = books
            .filter((book) => Sales[book.id] && Sales[book.id] > 0)
            .map((book) => ({
                ...book,
                sales: Sales[book.id],
            }));

        searchBooks.sort((a, b) => b.sales - a.sales);
        this.setState({ orders, books, searchBooks:searchBooks, originBooks:searchBooks});
    }
    handleTimePickerChange = (value) => {
        if(value === null) {
            this.setState({searchBooks: this.state.originBooks});
            return;
        }
        const Sales = [];
        this.state.orders.map((orderitems) => {
            const orderDate = new Date(orderitems.createtime);
            const startDate = new Date(value[0].format('YYYY-MM-DD'));
            const endDate = new Date(value[1].format('YYYY-MM-DD'));
            if(orderDate >= startDate && orderDate <= endDate) {
                orderitems.bookOrders.map((order) => {
                    if(Sales[order.bookID])
                        Sales[order.bookID] += order.quantity;
                    else
                        Sales[order.bookID] = order.quantity;
                });
            }
        });
        const books = this.state.books;
        const searchBooks = books
            .filter((book) => Sales[book.id] && Sales[book.id] > 0)
            .map((book) => ({
                ...book,
                sales: Sales[book.id],
            }));

        searchBooks.sort((a, b) => b.sales - a.sales);
        this.setState({ searchBooks:searchBooks});
    };

    render = () => {
        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
                <DatePicker.RangePicker  onChange={this.handleTimePickerChange}
                                        style={{width:"50%", margin:"20px"}}
                />
                <ProList
                    style={{margin:"20px", width:"75%"}}
                    search={
                        false
                    }
                    rowKey="name"
                    headerTitle="全部书籍"
                    pagination={{
                        pageSize:this.state.searchBooks.length, // 展示总数
                    }}
                    dataSource={this.state.searchBooks}
                    showActions="hover"
                    metas={{
                        title: {
                            dataIndex: 'title',
                            title: '书名',
                        },
                        description: {
                            title: 'ISBN-13',
                            dataIndex: 'isbn',
                            render: (_, row) => {
                                return (
                                    <span>
                                        <span>ISBN-13: {row.isbn} </span>
                                    </span>
                                );
                            },
                        },
                        avatar: {
                            dataIndex: 'cover',
                            search: false,
                        },
                        subTitle: {
                            render: (_, row) => {
                                return (
                                    <Space size={0}>
                                        <Tag color="gold">id：{row.id}</Tag>
                                        <Tag color={row.sales >= 30 ? 'orange' : 'blue'}>销量：{row.sales}</Tag>
                                    </Space>
                                );
                            },
                            search: false,
                        },
                        actions: {
                            render: (text, row) => [
                            ],
                            search: false,
                        },
                    }}
                />
            </Layout>
        );
    }
}

export default SalesView;