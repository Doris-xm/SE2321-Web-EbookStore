import { LockOutlined, UserOutlined } from '@ant-design/icons';
import {Button, Checkbox, Form, Input, message} from 'antd';
import "../css/login.css";
import { useNavigate} from "react-router-dom";
import React from "react";
const LoginForm = ({UserData, onLogin}) => {
    // const props = this.props;
    const onFinish = (values) => {
        console.log('Received values of form: ', values);
        console.log(UserData);
        const targetUser = UserData.find((user) => user.id === 1);

        if (values.username === targetUser.name ) {
            if (values.password === targetUser.password) {
                targetUser.online = true; // 将 id 更改为新值
                onLogin(targetUser); // 调用 App 组件传递的 onLogin 函数
                navigate("/");
                return;
            }
            message.error('密码错误。用户名：dxm  密码：dxm');
            return;
        }
        message.error('用户名不存在。用户名：dxm  密码：dxm');
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
                        message: 'Please input your Username!',
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
                        message: 'Please input your Password!',
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
                    <Checkbox>Remember me</Checkbox>
                </Form.Item>

                <a className="login-form-forgot" href="">
                    Forgot password
                </a>
            </Form.Item>

            <Form.Item style={{alignItems:"center"}}>
                <Button type="primary" htmlType="submit" className="login-form-button">
                   Log in
                </Button>
                New here?  <a href="" >register now!</a>
            </Form.Item>
        </Form>
    );
};
export default LoginForm;
