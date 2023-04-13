
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

export const getBooks = async () => {
    let books = [];
    try {
        const response = await fetch('/books');
        const data = await response.json();
        books = data;
        console.log("getbooks", books);
    } catch (error) {
        console.error("Error fetching books:", error);
    }
    return books;
};
export const getBook = async (id) => {
    let book = null;
    try {
        const response = await fetch('/books');
        const data = await response.json();
        book = data.find((book) => book.id === parseInt(id));
        console.log("getbook", book);
    } catch (error) {
        console.error("Error fetching books:", error);
    }
    return book;
};
