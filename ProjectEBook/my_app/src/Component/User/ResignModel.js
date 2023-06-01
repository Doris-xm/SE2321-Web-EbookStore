import React, { useState } from 'react';
import {Button, Cascader, Form, Input, Modal} from 'antd';
import {checkNewMail, checkNewName, resignNewUser} from "../../Service/UserService";
const ResignModel: React.FC = () => {
    const [form] = Form.useForm();
    const [open, setOpen] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);

    const showModal = () => {
        setOpen(true);
    };

    const handleOk = () => {
        form.submit();
    };

    const handleCancel = () => {
        console.log('Clicked cancel button');
        setOpen(false);
    };
    const checkName = async (rule,value) => {
        // console.log("send",value);
        const result = await checkNewName(value);
        return result ? Promise.resolve() : Promise.reject('用户名已被注册');
    };
    const checkMail = async (rule,value) => {
        // console.log("send",value);
        const result = await checkNewMail(value);
        return result ? Promise.resolve() : Promise.reject('邮箱已被注册');
    };
    const onFinish = (values) => {
        setConfirmLoading(true);
        setTimeout(async () => {
            setOpen(false);
            setConfirmLoading(false);
            form.resetFields();
            await resignNewUser(values).then( // 调用 sendOrder 函数将订单信息发送到后端
                (res) => {
                   handleCancel();
                }
            );
        }, 1000);
    };

    return (
        <>
            没有账号？
            <a type="primary" onClick={showModal}>
                注册账号
            </a>
            <Modal
                title="注册新用户"
                open={open}
                onOk={handleOk}
                confirmLoading={confirmLoading}
                onCancel={handleCancel}
            >
                <Form
                    form={form} onFinish={onFinish}
                    labelCol={{ span: 4 }}
                    wrapperCol={{ span: 14 }}
                    layout="horizontal"
                    size={"middle"}
                    style={{ maxWidth: 600 }}
                >
                    <Form.Item label="用户名"
                               name="name"
                               rules={[{ required: true, message: '请输入用户名' },
                                   {max: 20, message: '用户名最多20位'},
                                   { validator: checkName },
                               ]}>
                        <Input />
                    </Form.Item>
                    <Form.Item label="密码"
                               name="pwd"
                               rules={[{ required: true, message: '请输入密码' }, { min: 3, message: '密码至少3位' }]}>
                        <Input.Password />
                    </Form.Item>
                    <Form.Item label="重复密码"
                               name="pwd2"
                               rules={[{ required: true, message: '请重复输入密码' },
                                   ({ getFieldValue }) => ({
                                       validator(_, value) {
                                           if (!value || getFieldValue('pwd') === value) {
                                               return Promise.resolve();
                                           }
                                           return Promise.reject(new Error('两次输入的密码不一致'));
                                       },
                                   }),
                               ]}>
                        <Input.Password />
                    </Form.Item>
                    <Form.Item label="邮箱"
                               name="email"
                               rules={[{ required: true, message: '请输入邮箱' },
                                   { pattern: /^.+@.+$/, message: '邮箱格式不正确' },
                                   { validator: checkMail },]}>
                        <Input />
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
};

export default ResignModel;