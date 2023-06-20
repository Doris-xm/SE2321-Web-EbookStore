import {ProForm, ProList} from '@ant-design/pro-components';
import {Button, Input, Layout, Modal, Space, Tag} from 'antd';
import React from "react";
import {banUsers, getAllUsers} from "../../Service/UserService";
import {deleteBooks, getBooks} from "../../Service/BookService";

export class ManageUserView extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { books: booksData };
        this.state = { books: [],
            selectedRowKeys: [],
            selectedIds: [],
            selectedTitles: [],
            openConfirm: false,
        };
    }
    onSelectChange = (selectedRowKeys) => {
        this.setState({ selectedRowKeys });
    };
    async componentDidMount() {
        const books = await getBooks();
        this.setState({ books});
    }
    confirm =(selection)=>{
        console.log(selection);
        return true;
    }

    render = () => {
        const { selectedRowKeys } = this.state;

        const rowSelection = {
            selectedRowKeys,
            onChange: (selectedKeys, selectedRows) => {
                const selectedBookIds = selectedRows.map((row) => row.id);
                const selectedBookTitles = selectedRows.map((row) => row.title);

                // 在这里可以使用 selectedBookIds 和 selectedBookTitles 做进一步的操作
                // ...

                this.setState({
                    selectedRowKeys: selectedKeys,
                    selectedIds: selectedBookIds,
                    selectedTitles: selectedBookTitles,
                })
            },
        };
        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
                <ProList
                    toolBarRender={() => {
                        return [
                            <Button key="3" type="primary">
                                上传书籍
                            </Button>,
                            <Button key="4" type="default"
                                    onClick={ () => {
                                        this.setState({ openConfirm: true });
                                    }}
                                    disabled={!selectedRowKeys || selectedRowKeys.length === 0}>
                                删除
                            </Button>,
                            <Modal
                                title="确认删除"
                                open={this.state.openConfirm}
                                onOk={async () => {
                                    await deleteBooks(this.state.selectedIds).then((res)=>{
                                        if(res){
                                            this.setState({ openConfirm: false ,selectedRowKeys:[]});
                                        }
                                        else {
                                            this.setState({ openConfirm: false})
                                        }
                                    });
                                }}
                                onCancel={() => {
                                    this.setState({ openConfirm: false })
                                }}
                                okText="确定"
                                cancelText="取消"
                            >
                                <p>是否确认删除选中的书籍？</p>
                                {this.state.selectedTitles.map((title, index) => (
                                    <p key={index}>{title}</p>
                                ))}
                            </Modal>
                        ];
                    }}
                    style={{margin:"20px", width:"75%"}}
                    search={
                        {
                            // filterType: 'light',
                            // placeholder: '请输入书名或ISBN-13',
                            // onSearch: (value) => {
                            //     console.log(value);
                            // },
                        }
                    }
                    rowKey="name"
                    headerTitle="全部书籍"
                    rowSelection={rowSelection}
                    pagination={{
                        pageSize:8,
                        total: this.state.books.length, // 展示总数
                    }}
                    dataSource={this.state.books}
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
                                        <span>,  作者: {row.author}</span>
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
                                        <Tag color={row.stocks <= 5 ? 'red' : 'green'}>库存：{row.stocks}</Tag>
                                        <Tag color={row.sales >= 30 ? 'orange' : 'blue'}>销量：{row.sales}</Tag>
                                    </Space>
                                );
                            },
                            search: false,
                        },
                        actions: {
                            render: (text, row) => [
                                <Button
                                    href={row.url}
                                    target="_blank"
                                    rel="noopener noreferrer"
                                    key="view"
                                    type={"default"}
                                >
                                    编辑
                                </Button>,
                            ],
                            search: false,
                        },
                    }}
                />
            </Layout>
        );
    }
}
export default ManageUserView;