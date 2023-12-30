import {ProForm, ProList} from '@ant-design/pro-components';
import {Button, Cascader, Form, Input, Layout, Modal, Space, Tag} from 'antd';
import React from "react";
import {addBook, deleteBooks, getBooks, modifyBook, searchAuthor, searchByLabel} from "../../Service/BookService";
import Search from "antd/es/input/Search";
import {Link} from "react-router-dom";

export class AllBooksView extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = { books: [],
            searchBooks: [],
            searchAuthor: "",
            searchLabel: [],
        };
    }
    async componentDidMount() {
        const books = await getBooks();
        this.setState({ books, searchBooks:books });
    }

    render = () => {

        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
                <Search
                    placeholder="输入书名或ISBN-13"
                    allowClear
                    enterButton="搜索"
                    size="large"
                    style={{width:"75%", margin:"20px"}}
                    onSearch={ (value) => {
                        if(value === "") {
                            this.setState({searchBooks: this.state.books});
                            return;
                        }
                        const searchBooks = this.state.books.filter((book) => {
                            return book.bookDetail.title.includes(value) || book.bookDetail.isbn.includes(value);
                        });
                        this.setState({searchBooks});
                        console.log("searchBooks: ", searchBooks);
                    }}
                />
                <Search
                    placeholder="输入书名查询作者"
                    allowClear
                    enterButton="搜索"
                    size="large"
                    style={{width:"75%", margin:"20px"}}
                    onSearch={ async (value) => {
                        if (value === "") {
                            this.setState({searchAuthor: ""});
                            return;
                        }
                        const searchBooks = await searchAuthor(value);
                        this.setState({searchAuthor: searchBooks.bookDetail.author});
                    }}
                />
                <h1 style={{margin:"20px"}}>{this.state.searchAuthor}</h1>
                <Search
                    placeholder="输入标签查询书籍"
                    allowClear
                    enterButton="搜索"
                    size="large"
                    style={{width:"75%", margin:"20px"}}
                    onSearch={ async (value) => {
                        if (value === "") {
                            this.setState({searchLabel: ""});
                            return;
                        }
                        const LabelBooks = await searchByLabel(value);
                        console.log("LabelBooks: ", LabelBooks);
                        this.setState({searchLabel: LabelBooks});
                    }}
                />
                <div style={{ margin: "20px" }}>
                    {this.state.searchLabel && this.state.searchLabel.map((book, index) => (
                        <div key={index}>
                            <h1>{book.title}</h1>
                            <div>
                                {book.type.map((type, i) => (
                                    <Tag key={i} color="blue">{type}</Tag>
                                ))}
                            </div>
                        </div>
                    ))}
                </div>
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
                            render: (_, row) => {
                                return (
                                    <Space size={0}>
                                        <span>{row.bookDetail.title}</span>
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
                                        <span>ISBN-13: {row.bookDetail.isbn} </span>
                                        <span>,  作者: {row.bookDetail.author}</span>
                                    </span>
                                );
                            },
                        },
                        avatar: {
                            dataIndex: 'cover',
                            render: (_, row) => {
                                return (
                                    <img
                                        src={row.bookDetail.cover}
                                        width={40}
                                        alt={row.bookDetail.title}
                                    />
                                );
                            },
                            search: false,
                        },
                        subTitle: {
                            render: (_, row) => {
                                return (
                                    <Space size={0}>
                                        <Tag color="gold">￥：{row.bookDetail.price}</Tag>
                                        <Tag color={row.stocks <= 5 ? 'red' : 'green'}>库存：{row.stocks}</Tag>
                                        <Tag color={row.sales >= 30 ? 'orange' : 'blue'}>销量：{row.sales}</Tag>
                                    </Space>
                                );
                            },
                            search: false,
                        },
                        actions: {
                            render: (text, row) => [
                                <Link
                                    to={{ pathname: '/bookDetails/' + row.id}}
                                    // onClick={this.showBookDetails.bind(this,info.id)}
                                    // target="_blank" //新页面
                                >
                                    查看详情
                                </Link>
                            ],
                            search: false,
                        },
                    }}
                />
            </Layout>
        );
    }
}
export default AllBooksView;