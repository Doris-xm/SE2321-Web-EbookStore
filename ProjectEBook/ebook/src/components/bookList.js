import React from 'react';
import {List} from 'antd'
import Book from './book'

export class BookList extends React.Component {
    render() {
        const {data} = this.props;
        return(
            <List
                grid={{gutter: 10, column: 4}}
                dataSource={data}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
        );
    }
}

export default BookList;