import React from "react";
import {Descriptions, Badge, Button} from 'antd';
import '../css/View.css'
import {Link} from "react-router-dom";
import {getUser} from "../Service/UserService";
import {handleLogout} from "../Service/UserService";

export class User extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user:null,
        };
    }
    async componentDidMount() {
        const user = await getUser();
        this.setState({ user });
    }
    render = () => {
        if(this.state.user === null) {
            return (
                <div  className={"notLogin-content"} style={{ margin:'center'}}>
                    请先登录
                    <br/>
                    <Button className="buttons" >
                        <Link to = '/login'>
                            登录
                        </Link>
                    </Button>
                </div>
            );
        }
        return (
            <div className="my-content" >
                <div className="horizon-content">
                    <img src={this.state.user.avatar} style={{width: 200,height:'auto'}}/>
                    <div className="buttons">
                        <Button type="primary">修改信息</Button>
                        <Button type="primary"  onClick={handleLogout}>
                            <Link to = '/login'>
                                退出账号
                            </Link>
                        </Button>
                    </div>
                </div>

                <div style={{ width: '95%' }}>
                    <Descriptions title="用户信息" bordered={false} >
                        <Descriptions.Item label="昵称">{this.state.user.name}</Descriptions.Item>
                        <Descriptions.Item label="账号">{this.state.user.id}</Descriptions.Item>
                        <Descriptions.Item label="来到书店">{this.state.user.Use_year}年</Descriptions.Item>
                        <Descriptions.Item label="邮箱">(未填写)</Descriptions.Item>
                        <Descriptions.Item label="电话" span={2}>
                            2019-04-24 18:00:00
                        </Descriptions.Item>
                        <Descriptions.Item label="状态" span={3}>
                            <Badge status="processing" text="在线" />
                        </Descriptions.Item>
                        <Descriptions.Item label="Negotiated Amount">$80.00</Descriptions.Item>
                        <Descriptions.Item label="Discount">$20.00</Descriptions.Item>
                        <Descriptions.Item label="Official Receipts">$60.00</Descriptions.Item>
                        <Descriptions.Item label="个人简介">
                            大家好
                            <br/>
                            Hello
                            <br/>
                            大家好
                            <br/>
                            大家好
                            <br/>
                            大家好
                        </Descriptions.Item>
                    </Descriptions>
                </div>

            </div>

        );
    };
};
export default User;