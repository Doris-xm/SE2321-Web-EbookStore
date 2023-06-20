import {Link} from "react-router-dom";
import React from "react";
import {message} from "antd";
import {postRequest} from "../util/ajax";

export const getBooks = async () => {
    let books = [];
    try {
        const response = await fetch('/books');
        books = await response.json();
        console.log("getbooks", books);
    } catch (error) {
        console.error("Error fetching books:", error);
    }
    return books;
};
export const getBook = async (id) => {
    const url = '/api/book';
    const data = {
        id: id,
    };
    const callback = (data) => {
        return data.data;
    };
    return postRequest(url, data, callback);
};
export const deleteBooks = async (bookIds) => {
    console.log("deleteBooks", bookIds);
    const url = '/api/deleteBooks';
    const data = {
        bookIds: bookIds,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};
export const modifyBook = async (data) => {
    console.log("modifyBook", data);
    const url = '/api/modifyBook';
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};
export const addBook = async (data) => {
    console.log("add", data);
    const url = '/api/addBook';
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};

// export const getBookName = async (id) => {
//     const book = await getBook(id);
//     console.log("getbookName", book.title);
//     return book? book.title : "Not Found";
//     // return book? (
//     //     <Link to={`/bookDetails/${id}`}>
//     //         {book.title}
//     //     </Link>
//     // ):(
//     //     "Loading"
//     // );
// };
