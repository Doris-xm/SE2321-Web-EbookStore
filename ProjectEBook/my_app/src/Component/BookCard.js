import React from 'react';
import { Card } from 'antd';
import '../css/Book.css'
import '../css/View.css'

import {Link} from 'react-router-dom'

const { Meta } = Card;

export class BookCard extends React.Component{

    render() {

        const {info} = this.props;

        return (
            <Link
                to={{ pathname: '/bookDetails/' + info.id}}
                // onClick={this.showBookDetails.bind(this,info.id)}
                // target="_blank" //新页面
            >
                <Card
                    className={'book-card'}
                    hoverable
                    cover={<img alt="load error" src={info.cover} className={"bookImg"}/>}
                    // onClick={this.showBookDetails.bind(this, info.id)}
                >
                    <Meta
                        title={`${info.title}`}
                        description={
                          <>
                              <div>{info.author}</div>
                              <div>{` ¥ ${info.price}`}</div>
                          </>
                        }
                    />
                </Card>
            </Link>

        );
    }

}

