import { LockOutlined, UserOutlined } from '@ant-design/icons';
import {Button, Checkbox, Form, Input, message} from 'antd';
import "../../css/login.css";
import {Link, useNavigate} from "react-router-dom";
import React from "react";
import {checkUser} from "../../Service/UserService";
import ResignModel from "./ResignModel";
const LoginForm = () => {
    // const props = this.props;
    const onFinish = async (values) => {
        console.log('Received values of form: ', values);
        await checkUser(values.username, values.password).then(targetUser => {
            console.log("targetUSer",targetUser);
            if (targetUser === null ) {
                localStorage.setItem('authToken', -1);
                return;
            }
            // localStorage.setItem('User', targetUser);
            localStorage.setItem('User',  JSON.stringify(targetUser));

            localStorage.setItem('authToken', targetUser.id);
            navigate("/");
        });

    };
    const navigate = useNavigate();
    return (
        <Form
            name="normal_login"
            className="login-form"
            initialValues={{
                remember: true,
            }}
            onFinish={onFinish}
        >
            <Form.Item
                name="username"
                rules={[
                    {
                        required: true,
                        message: '请输入用户名!',
                    },
                ]}
            >
                <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[
                    {
                        required: true,
                        message: '请输入密码!',
                    },
                ]}
            >
                <Input
                    prefix={<LockOutlined className="site-form-item-icon" />}
                    type="password"
                    placeholder="Password"
                />
            </Form.Item>
            <Form.Item>
                <Form.Item name="remember" valuePropName="checked" noStyle>
                    <Checkbox>记住密码</Checkbox>
                </Form.Item>

                <a className="login-form-forgot" href="src/Component">
                    忘记密码
                </a>
            </Form.Item>

            <Form.Item style={{alignItems:"center"}}>
                <Button type="primary" htmlType="submit" className="login-form-button">
                   登录
                </Button>
                <br/>
                <Button className="login-form-button" style={{marginTop:"10px"}}>
                    <Link to={"/"}>
                        游客访问
                    </Link>
                </Button>
                <div>
                    <ResignModel/>
                    {/*<ResignModel cartData = {this.state.cartData} onClearCart={this.handleClearCart}/>*/}
                </div>
            </Form.Item>
        </Form>
    );
};
export default LoginForm;
