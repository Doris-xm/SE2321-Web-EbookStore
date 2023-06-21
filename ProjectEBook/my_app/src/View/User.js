import React from "react";
import {Descriptions, Badge, Button, Card, List, Layout} from 'antd';
import '../css/View.css'
import {Link} from "react-router-dom";
import {getUser} from "../Service/UserService";
import {handleLogout} from "../Service/UserService";
import UserImage from "../Component/User/UserImage";

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
            <Layout style={{backgroundColor:"transparent",display:"flex",alignItems:"center"}} >
                <h1 className="h1">
                    欢迎回来! {this.state.user.nickname} !</h1>
                <Card
                    className="glass-container"
                    style={{  marginTop: 30,width:"50%"}}
                    bodyStyle={{
                        display: 'flex',
                        alignItems: 'center',
                        width: '100%',
                    }}
                >
                    <div style={{alignItems:"flex-start"}}>
                        <img
                            src={this.state.user.avatar}
                            alt="头像"
                            style={{width: 200,height:'auto'}}
                        />
                    </div>

                    <div
                        style={{
                            width: '100%',
                            display: 'flex',
                            alignItems: 'center',
                            flexDirection:'column',
                            justifyContent: 'space-between',
                            color: 'black', // 设置字体颜色为黑色
                            lineHeight: '2.5', // 设置行间距
                        }}
                    >
                        <div style={{ fontSize: '20px', fontWeight: 'bold' }}>
                            昵称：{this.state.user.nickname}
                        </div>
                        <div style={{  fontSize: '16px' }}>
                            邮箱：{this.state.user.email}
                        </div>
                        <div style={{  fontSize: '16px' }}>
                            来到Bookstore的第{this.state.user.years}年
                        </div>
                        <div style={{  fontSize: '16px' }}>
                            个人简介：{this.state.user.introduce}
                        </div>
                    </div>
                </Card>
                <div style={{marginTop:15}} className="buttons">
                    {/*<Button type="primary">修改信息</Button>*/}
                    <Button type="primary"  onClick={handleLogout}>
                        <Link to = '/login'>
                            退出账号
                        </Link>
                    </Button>
                </div>
                <UserImage user={this.state.user}  />
            </Layout>

        );
    };
};
export default User;