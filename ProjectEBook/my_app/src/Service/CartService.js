import {getUser} from "./UserService";
import {message} from "antd";

// export const AddToCart = (book) => {
//     // 存储在localStorage中
//     var cart = localStorage.getItem("cart");
//     if (cart == null) {
//         localStorage.setItem("cart", "[]");
//         cart = [];
//     } else {
//         cart = JSON.parse(cart);
//     }
//     // 检查书籍是否已经存在于购物车中
//     const existingBookIndex = cart.findIndex((item) => item.id === book.id);
//
//     // 如果书籍已经存在，则更新其数量
//     if (existingBookIndex !== -1) {
//         cart[existingBookIndex].quantity += 1;
//     } else {
//         // 如果书籍不存在，则将其添加到购物车并设置初始数量为1
//         book.quantity = 1;
//         cart.push(book);
//     }
//
//     localStorage.setItem("cart", JSON.stringify(cart));
//     // localStorage.clear();
//     console.log(localStorage.getItem("cart"));
// };
export const AddToCart = async (bookID) => {
    const user = await getUser();
    if(user === null){
        message.error('请先登录!');
        return;
    }
    const userID = user.id;
    console.log("addBookToCart", bookID);
    fetch('http://localhost:8001/addtocart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem("token")
        },
        body: JSON.stringify([userID, bookID]),
    })
        .then(response => {
            if (!response.ok) {
                message.error('添加失败!');
                throw new Error('Failed to send order');
            }
            console.log('添加成功!');
            message.success('添加成功!');
            return response.text(); // 返回响应的文本内容
        })
        .then(data => console.log(data))
        .catch(error => console.error(error));
};
// export const getCart = () => {
//     // 存储在localStorage中
//     var cart = localStorage.getItem("cart");
//     if (cart == null) {
//         return [];
//     } else {
//         cart = JSON.parse(cart);
//     }
//
//     localStorage.setItem("cart", JSON.stringify(cart));
//     return cart;
// };
export const getCart = async () => {
    let cart = [];
    const user = await getUser();
    const id = user.id;
    try {
        const response = await fetch(`/cart?userID=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'token': localStorage.getItem("token")
            },
        });
        cart = await response.json();
        console.log("getcart", cart);
    } catch (error) {
        console.error("Error fetching cart:", error);
    }
    return cart;
};

export const clearCart = async () => {
    const user = await getUser();
    const userID = user.id;
    fetch('http://localhost:8001/cartclear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem("token")
        },
        body: JSON.stringify(userID),
    })
        .then(response => {
            if (!response.ok) {
                message.error('购物车清空失败!');
                throw new Error('Failed to clear cart');
            }
            console.log('购物车清空成功!');
            return response.text(); // 返回响应的文本内容
        })
        .then(data => console.log(data))
        .catch(error => console.error(error));
};

// export const ChangeQuantity = (ID, quantity) => {
//     //存储在localStorage中
//     var cart = localStorage.getItem("cart");
//     if (cart == null) {
//         localStorage.setItem("cart", "[]");
//         cart = [];
//     } else {
//         cart = JSON.parse(cart);
//     }
//     const existingBookIndex = cart.findIndex((item) => item.id === ID);
//     console.log("index",existingBookIndex);
//
//     // 如果书籍已经存在，则更新其数量
//     if (existingBookIndex !== -1) {
//         if(quantity === 0){
//             // cart[existingBookIndex].quantity = quantity;
//             cart.splice(existingBookIndex,1);
//             console.log("cart",cart)
//         }
//         else {
//             cart[existingBookIndex].quantity = quantity;
//         }
//     } else {
//         return;
//     }
//
//     localStorage.setItem("cart", JSON.stringify(cart));
//     console.log(localStorage.getItem("cart"));
// };
export const ChangeQuantity = async (bookID, quantity) => {
    const user = await getUser();
    const userID = user.id;
    fetch('http://localhost:8001/cartquantity', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'token': localStorage.getItem("token")
        },
        body: JSON.stringify([userID, bookID, quantity]),
    })
        .then(response => {
            if (!response.ok) {
                message.error('改变数量失败!');
                throw new Error('Failed to send order');
            }
            console.log('改变数量成功!');
            return response.text(); // 返回响应的文本内容
        })
        .then(data => console.log(data))
        .catch(error => console.error(error));
};