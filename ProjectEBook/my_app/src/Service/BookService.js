import {Link} from "react-router-dom";
import React from "react";

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
    let book = null;
    try {
        const response = await fetch(`/book?id=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        book = await response.json();
        console.log("getbook", book);
    } catch (error) {
        console.error("Error fetching books:", error);
    }
    return book;
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
