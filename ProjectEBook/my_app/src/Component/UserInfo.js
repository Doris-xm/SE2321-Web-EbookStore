import React from 'react';
import { Avatar, Dropdown, Menu} from 'antd';
import '../css/index.css'
import '../css/View.css'
import {Link} from "react-router-dom";
import {checkSession, getUser} from "../Service/UserService";
import {handleLogout} from "../Service/UserService";


export class UserInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user:null,
        };
    }
    async componentDidMount() {
        const user = getUser();
        if(user === null)
            return;
        await checkSession(user.id).then(res => {
            if(res) {
                this.setState({ user });
            }
        });
        // this.setState({ user });
    }


    render() {

        const menu = (
            <Menu>
                <Menu.Item>
                    <Link to={{pathname: 'my/info'}}>
                        个人信息
                    </Link>
                </Menu.Item>
                <Menu.Item onClick={handleLogout}>
                    <Link to="/login">
                        登出账号
                    </Link>
                </Menu.Item>

            </Menu>
        );
        const menu_notLogin = (
            <Menu>
                <Menu.Item>
                    <Link to = '/login'>
                        登录
                    </Link>
                </Menu.Item>
                <Menu.Item>
                    <Link to = '/login'> {/*TODO*/}
                        注册
                    </Link>
                </Menu.Item>
            </Menu>
        );
        const userNone = require('../asset/UserAvatar/UserNull.jpg');
        console.log("Avater",this.state.user)
        return(
            <div id="avatar">
                {/*<span className="name">Hi, {user.username}</span>*/}
                {this.state.user ? (
                    <Dropdown overlay={menu} placement="bottomRight">
                        <Avatar src={this.state.user.avatar} style={{ cursor: "pointer" }} />

                    </Dropdown>
                ) : (
                    <Dropdown overlay={menu_notLogin} placement="bottomRight">
                        <Avatar src={userNone} style={{ cursor: "pointer" }} />
                    </Dropdown>
                )}
            </div>
        );
    }
}