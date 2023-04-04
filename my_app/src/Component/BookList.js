import React from 'react';
import { List } from 'antd';
import { BookCard } from './BookCard';
import { booksData } from '../data/book'; // 导入自定义图书数据

export class BookList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { books: booksData }; // 将自定义图书数据存储在组件状态中的 books 属性中
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
                        <BookCard info={item} />
                    </List.Item>
                )}
            />
        );
    }
}
