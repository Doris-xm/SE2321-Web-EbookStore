import { message } from 'antd';
import {getUser} from "./UserService";
import {getCart} from "./CartService";
import {clearCart} from "./CartService";
export const getOrders = async (id) => {
    let orders = [];
    try {
        const response = await fetch(`/orders?id=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        orders = await response.json();
        console.log("getorders", orders);
    } catch (error) {
        console.error("Error fetching orders:", error);
    }
    return orders;
};

/**
 * 发送订单至后端
 *
 */
export const sendOrder = async (order,cartData) => {
    console.log("order", order);
    console.log("cartData", cartData);
    // let cart = await getCart();
    const bookIDs = cartData.map((item)=> item.bookID); // 提取购物车中的书籍 id
    const bookNums = cartData.map((item) => item.quantity); // 提取购物车中的书籍数量
    const totalPrice = cartData.map((item) => item.price * item.quantity); // 计算购物车总价

    /**
     * 构造订单对象,格式示例为：
     * {
     * receiver: '张三',
     * phone: '12345678901',
     * address: '广东省广州市天河区',
     * address_detail: '中山大道西',
     * totalPrice: [11.1, 11.1, 11.1]
     * bookIDs: [1, 2, 3],
     * bookNums: [1, 2, 3],
     * orderID: 0, 这个依靠数据库自增，前端不设值
     * state: 0,
     * userID: 1,
     * createtime: '2020-12-12 12:12:12'
     * }
     */
    const orderCommit = {
        receiver: order.name,
        phone: order.phone,
        address: order.address.join(''),
        address_detail: order.detail,
        totalPrice,
        bookIDs,
        bookNums,
        orderID: 0,
        state: 1,
        userID: getUser().id,
        createtime: new Date().toISOString()
    };
    console.log("orderCommit", orderCommit);
    fetch('http://localhost:8001/sendorders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderCommit)
    })
        .then(response => {
            if (!response.ok) {
                message.error('订单提交失败!');
                throw new Error('Failed to send order');
            }
            console.log('Order sent successfully!');
            message.success('订单提交成功!');
            clearCart();
            return response.text(); // 返回响应的文本内容
        })
        .then(data => console.log(data))
        .catch(error => console.error(error));
};

