import React from 'react';
import { List } from 'antd';
import { BookCard } from './BookCard';
// import { booksData } from '../data/book';
import {getBooks} from "../../Service/BookService"; // 导入自定义图书数据

export class BookList extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { books: booksData };
        this.state = { books: [] };
    }
    async componentDidMount() {
        const books = await getBooks();
        this.setState({ books });
    }

    render() {

        return (
            <List
                grid={{ gutter: 10, column: 4 }}
                dataSource={this.state.books}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}
                renderItem={item => (
                    <List.Item>
                        <BookCard info={item}  />
                    </List.Item>
                )}
            />
        );
    }
}
