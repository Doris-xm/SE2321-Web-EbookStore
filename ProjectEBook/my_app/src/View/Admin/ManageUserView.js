import {ProForm, ProList} from '@ant-design/pro-components';
import {Button, Input, Layout, Space, Tag} from 'antd';
import React from "react";
import {banUsers, getAllUsers} from "../../Service/UserService";

export class ManageUserView extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { books: booksData };
        this.state = { users: [] };
    }
    async componentDidMount() {
        const users_detail = await getAllUsers();
        const users = users_detail.map(user => ({
            id: user.userId,
            userName: user.userName,
            avatar: user.user.avatar,
            isBan: user.ban,
            email: user.user.email,
        }));
        this.setState({ users:users});
        console.log(users);
    }

    render = () => {
        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
            <ProList
                style={{margin:"20px", width:"75%"}}
                search={
                   false
                }
                rowKey="name"
                headerTitle="用户列表"
                pagination={{
                    pageSize: 5,
                    total: this.state.users.length, // 展示总数
                }}
                dataSource={this.state.users}
                showActions="hover"
                metas={{
                    title: {
                        dataIndex: 'userName',
                        title: '用户名',
                    },
                    description: {
                        dataIndex: 'email',
                        title: '邮箱',
                    },
                    avatar: {
                        dataIndex: 'avatar',
                        search: false,
                    },
                    subTitle: {
                        render: (_, row) => {
                            return (
                                <Space size={0}>
                                    <Tag color="blue">{row.id}</Tag>
                                    <Tag color={row.isBan ? 'red' : 'green'}>{row.isBan ? '已禁用' : '正常'}</Tag>
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
                                onClick={() => {
                                    const isBan = !row.isBan;
                                    if(banUsers(row.id, !row.isBan)){
                                        // 前端立刻更新
                                        const users = this.state.users.map(user => {
                                            if (user.id === row.id) {
                                                user.isBan = isBan;
                                            }
                                            return user;
                                        });
                                        this.setState(users);
                                    }
                                }}
                            >
                                {row.isBan?"解禁":"禁用"}
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