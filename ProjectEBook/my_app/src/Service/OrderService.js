import { message } from 'antd';
import {getUser} from "./UserService";
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
export const sendOrder = (order) => {
    let cart = getCart();
    const bookIDs = cart.map(item => item.id); // 提取购物车中的书籍 id
    const bookNums = cart.map(item => item.quantity); // 提取购物车中的书籍数量

    /**
     * 构造订单对象,格式示例为：
     * {
     * receiver: '张三',
     * phone: '12345678901',
     * address: '广东省广州市天河区',
     * address_detail: '中山大道西',
     * totalPrice: 100,
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
        totalPrice: cart.reduce((total, item) => total + item.price * item.quantity, 0), // 计算购物车总价
        bookIDs,
        bookNums,
        orderID: 0,
        state: 0,
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

export const AddToCart = (book) => {
    // 存储在localStorage中
    var cart = localStorage.getItem("cart");
    if (cart == null) {
        localStorage.setItem("cart", "[]");
        cart = [];
    } else {
        cart = JSON.parse(cart);
    }
    // 检查书籍是否已经存在于购物车中
    const existingBookIndex = cart.findIndex((item) => item.id === book.id);

    // 如果书籍已经存在，则更新其数量
    if (existingBookIndex !== -1) {
        cart[existingBookIndex].quantity += 1;
    } else {
        // 如果书籍不存在，则将其添加到购物车并设置初始数量为1
        book.quantity = 1;
        cart.push(book);
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    // localStorage.clear();
    console.log(localStorage.getItem("cart"));
};

export const getCart = () => {
    // 存储在localStorage中
    var cart = localStorage.getItem("cart");
    if (cart == null) {
        return [];
    } else {
        cart = JSON.parse(cart);
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    return cart;
};

export const clearCart = () => {
    localStorage.clear();
    //TODO 数据同步消失
};

export const ChangeQuantity = (ID, quantity) => {
    //存储在localStorage中
    var cart = localStorage.getItem("cart");
    if (cart == null) {
        localStorage.setItem("cart", "[]");
        cart = [];
    } else {
        cart = JSON.parse(cart);
    }
    const existingBookIndex = cart.findIndex((item) => item.id === ID);
    console.log("index",existingBookIndex);

    // 如果书籍已经存在，则更新其数量
    if (existingBookIndex !== -1) {
        if(quantity === 0){
            // cart[existingBookIndex].quantity = quantity;
            cart.splice(existingBookIndex,1);
            console.log("cart",cart)
        }
        else {
            cart[existingBookIndex].quantity = quantity;
        }
    } else {
        return;
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    console.log(localStorage.getItem("cart"));
};
