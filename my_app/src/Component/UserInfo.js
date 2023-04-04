import React from 'react';
import { Avatar, Dropdown, Menu} from 'antd';
import '../css/index.css'
import {Link} from "react-router-dom";


export class UserInfo extends React.Component {

    render() {

        const menu = (
            <Menu>
                <Menu.Item>
                    <Link to={{pathname: 'my/info'}}>
                        展示个人信息
                    </Link>
                </Menu.Item>
                <Menu.Item>
                    {/*<a>*/}
                    {/*    登出账号*/}
                    {/*</a>*/}
                    <Link to = '/login'>
                        登出账号
                    </Link>
                </Menu.Item>
            </Menu>
        );

        const {user} = this.props;
        console.log(user);



        return(
            <div id="avatar">
                <span className="name">Hi, {user.username}</span>
                <Dropdown overlay={menu} placement="bottomRight">
                    <Avatar src={user.avatar} style={{cursor:"pointer"}}/>
                </Dropdown>
            </div>
        );
    }
}