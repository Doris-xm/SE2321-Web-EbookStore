import {ProForm, ProList} from '@ant-design/pro-components';
import {Button, Cascader, Form, Input, Layout, Modal, Space, Tag} from 'antd';
import React from "react";
import {addBook, deleteBooks, getBooks, modifyBook} from "../../Service/BookService";
import Search from "antd/es/input/Search";

export class ManageUserView extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = { books: [],
            searchBooks: [],
            selectedRowKeys: [],
            selectedIds: [],
            selectedTitles: [],
            openConfirm: false,
            openEdit: false,
            openUpload: false,
        };
    }
    async componentDidMount() {
        const books = await getBooks();
        this.setState({ books, searchBooks:books });
    }

    render = () => {
        const { selectedRowKeys } = this.state;

        const rowSelection = {
            selectedRowKeys,
            onChange: (selectedKeys, selectedRows) => {
                console.log('selectedRows: ', selectedRows)
                const selectedBookIds = selectedRows.map((row) => row.id);
                const selectedBookTitles = selectedRows.map((row) => row.title);

                this.setState({
                    selectedRowKeys: selectedKeys,
                    selectedIds: selectedBookIds,
                    selectedTitles: selectedBookTitles,
                })
            },
        };
        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
                <Search
                    placeholder="输入书名或ISBN-13"
                    allowClear
                    enterButton="搜索"
                    size="large"
                    style={{width:"75%", margin:"20px"}}
                    onSearch={async (value) => {
                        if(value === "") {
                            this.setState({searchBooks: this.state.books});
                            return;
                        }
                        const searchBooks = this.state.books.filter((book) => {
                            return book.title.includes(value) || book.isbn.includes(value);
                        });
                        this.setState({searchBooks});
                        console.log("searchBooks: ", searchBooks);
                    }}
                />
                <ProList
                    toolBarRender={() => {
                        return [
                            <Button key="3" type="primary" onClick={()=>{this.setState({openUpload: true})}}>
                                上传书籍
                            </Button>,
                            <Modal
                                title="上传新的书籍"
                                visible={this.state.openUpload}
                                onOk={() => {
                                    this.formRef.submit();
                                }}
                                onCancel={() => {
                                    this.setState({ openUpload: false })
                                }}
                            >
                                <Form
                                    onFinish={(values) => {
                                        this.setState({ openUpload: false })
                                        addBook(values).then(async (res) => {
                                            if (res) {
                                                const books = await getBooks();
                                                this.setState({books});
                                            }
                                        });
                                    }}
                                    labelCol={{ span: 4 }} wrapperCol={{ span: 14 }} layout="horizontal"
                                    size={"middle"} style={{ maxWidth: 600 }}
                                    ref={(formRef) => (this.formRef = formRef)}
                                >
                                    <Form.Item label="书名"  name="title"
                                               rules={[{ required: true,  }]}>
                                        <Input />
                                    </Form.Item>
                                    <Form.Item label="ISBN-13" name="isbn"
                                               rules={[ { required: true, max: 13,min: 13 }]}>
                                        <Input />
                                    </Form.Item>
                                    <Form.Item label="作者" name="author"
                                               rules={[{ required: true }]}>
                                        <Input />
                                    </Form.Item>
                                    <Form.Item label="价格" name="price"
                                               rules={[{ required: true },
                                                   {pattern: new RegExp(/^[0-9]+(\.[0-9]+)?$/), message: '请输入数字'}
                                               ]}>
                                        <Input />
                                    </Form.Item>
                                    <Form.Item label="库存" name="stocks"
                                               rules={[{ required: true },
                                                   {pattern: new RegExp(/^[0-9]+$/), message: '请输入正整数'}
                                               ]}>
                                        <Input />
                                    </Form.Item>
                                    <Form.Item label="封面URL" name="cover"
                                               rules={[{ required: false },]}>
                                        <Input />
                                    </Form.Item>
                                </Form>
                            </Modal>,
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
                                    await deleteBooks(this.state.selectedIds).then(async (res) => {
                                        if (res) {
                                            this.setState({openConfirm: false, selectedRowKeys: []});
                                            const books = await getBooks();
                                            this.setState({books});
                                        } else {
                                            this.setState({openConfirm: false})
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
                       false
                    }
                    rowKey="name"
                    headerTitle="全部书籍"
                    rowSelection={rowSelection}
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
                                        <span>,  作者: {row.author}</span>
                                        <span>,  价格: {row.price}</span>
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
                                    href={row.url} key="view" type={"default"}
                                    onClick={() => {
                                        this.setState({ openEdit: true });
                                    }}
                                >
                                    编辑
                                </Button>,
                                <Modal
                                    title="修改书籍信息"
                                    visible={this.state.openEdit}
                                    onOk={(value) => {
                                        this.formRef.submit();
                                        this.setState({ openEdit: false })
                                    }}
                                    onCancel={() => {
                                        this.formRef.resetFields(); // 重置表单字段值
                                        this.setState({ openEdit: false })
                                    }}
                                >
                                    <Form
                                        onFinish={(values) => {
                                            values.id = row.id;
                                            modifyBook(values).then(async (res) => {
                                                if (res) {
                                                    const books = await getBooks();
                                                    this.setState({books});
                                                }
                                            });
                                        }}
                                        labelCol={{ span: 4 }} wrapperCol={{ span: 14 }} layout="horizontal"
                                        size={"middle"} style={{ maxWidth: 600 }}
                                        ref={(formRef) => (this.formRef = formRef)}
                                    >
                                        <Form.Item label="书名"  name="name"
                                                   initialValue={row.title}
                                                   rules={[{ required: false,  }]}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="ISBN-13" name="isbn"
                                                   initialValue={row.isbn}
                                                   rules={[ { required: false, max: 13,min: 13 }]}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="作者" name="author"
                                                   initialValue={row.author}
                                                   rules={[{ required: false }]}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="价格" name="price"
                                                   initialValue={row.price}
                                                   rules={[{ required: false },
                                                       {pattern: new RegExp(/^[0-9]+(\.[0-9]+)?$/), message: '请输入数字'}
                                                   ]}>
                                            <Input />
                                        </Form.Item>
                                        <Form.Item label="库存" name="stocks"
                                                    initialValue={row.stocks}
                                                   rules={[{ required: false },
                                                       {pattern: new RegExp(/^[0-9]+$/), message: '请输入正整数'}
                                                   ]}>
                                            <Input />
                                        </Form.Item>
                                    </Form>
                                </Modal>
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