import React, { useState } from 'react';
import {Button, Cascader, Form, Input, message, Modal} from 'antd';
import {sendOrder} from "../../Service/OrderService"
import {CityOptions} from "../../data/city"
import {getBook} from "../../Service/BookService";
const OrderModel: React.FC = ({cartData,onClearCart}) => {
    const [form] = Form.useForm();
    const [open, setOpen] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);

    const showModal = async () => {
        for (let i = 0; i < cartData.length; i++) {
            const book = await getBook(cartData[i].bookID);
            console.log(book);
            if (cartData[i].quantity > book.stocks) {
                message.error(book.title+"库存不足,当前库存" + book.stocks + "本");
                return;
            }
        }
        setOpen(true);
    };

    const handleOk = () => {
        form.submit();
    };

    const handleCancel = () => {
        console.log('Clicked cancel button');
        setOpen(false);
    };

    const onFinish = (values) => {
        setConfirmLoading(true);
        setTimeout(async () => {
            setOpen(false);
            setConfirmLoading(false);
            form.resetFields();
            await sendOrder(values, cartData).then( // 调用 sendOrder 函数将订单信息发送到后端
                (res) => {
                    if (res) {
                        // alert("订单提交成功");
                        onClearCart(); // 调用 onClearCart 函数清空购物车
                    } else {
                        // alert("订单提交失败");
                    }
                }
            );
        }, 1000);
    };

    return (
        <>
            <Button type="primary" onClick={showModal}>
                付款
            </Button>
            <Modal
                title="订单信息填写"
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
                    <Form.Item label="收件人"
                               name="name"
                               rules={[{ required: true, message: '请输入收件人姓名' }]}>
                        <Input />
                    </Form.Item>
                    <Form.Item label="手机号"
                               name="phone"
                               rules={[{ required: true, message: '请输入手机号' },
                                   {pattern: /^1[3456789]\d{9}$/,
                                       message: '请输入正确的手机号',},
                               ]}>
                        <Input />
                    </Form.Item>
                    <Form.Item label="收货地址"
                               name="address"
                               rules={[{ required: true, message: '请选择收货地址' }]}>
                        <Cascader
                            options={CityOptions}
                        />
                    </Form.Item>
                    <Form.Item label="详细地址"
                               name="detail"
                               rules={[{ required: true, message: '请输入详细地址' }]}>
                        <Input />
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
};

export default OrderModel;