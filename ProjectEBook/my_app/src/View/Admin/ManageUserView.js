import {ProForm, ProList} from '@ant-design/pro-components';
import {Button, Input, Layout, Space, Tag} from 'antd';
import React from "react";
import {banUsers, getAllUsers} from "../../Service/UserService";
import {getAllOrders} from "../../Service/OrderService";

export class ManageUserView extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { books: booksData };
        this.state = { users: [],
            sorted: false,
            sortedUsers: [],};
    }
    async componentDidMount() {
        const users_detail = await getAllUsers();
        const orders = await getAllOrders();

        const userExpenses = {}; // 用于保存每个用户的消费额度

        orders.forEach(order => {
            if (userExpenses[order.userID]) {
                userExpenses[order.userID] += order.totalprice;
            } else {
                userExpenses[order.userID] = order.totalprice;
            }
        });

        const users = users_detail.map(user => ({
            id: user.userId,
            userName: user.userName,
            avatar: user.user.avatar,
            isBan: user.ban,
            email: user.user.email,
            expense: userExpenses[user.userId] || 0, // 获取每个用户的消费额度，如果不存在则默认为0
        }));

        this.setState({ users: users,sortedUsers: users});
    }


    render = () => {
        return (
            <Layout style={{alignItems:"center",background:"transparent"}}>
            <ProList
                toolBarRender={() => {
                    return [
                        <Button key="3" type="default" onClick={()=>{
                            if( !this.state.sorted){
                                const sortedUsers = this.state.users.sort((a, b) => {
                                    return b.expense - a.expense;
                                });
                                this.setState({sortedUsers:sortedUsers,sorted:!this.state.sorted})
                                return;
                            }
                            const sortedUsers = this.state.users.sort((a, b) => {
                                return a.id - b.id;
                            });
                            this.setState({sortedUsers,sorted:!this.state.sorted})
                        }}>
                            {this.state.sorted?"编号排序":"消费排序"}
                        </Button>,
                        ]
                }}
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
                dataSource={this.state.sortedUsers}
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
                                    <Tag color='orange'>总消费：{row.expense}</Tag>
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
                                        this.setState({users, sortedUsers: users});
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