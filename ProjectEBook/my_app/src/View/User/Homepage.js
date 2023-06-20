import React from "react";
import {Content, Header} from "antd/es/layout/layout";
import {Carousel, Layout} from "antd";
import BookCarousel from "../../Component/Book/BookCarousel";
import {BookList} from "../../Component/Book/BookList";
import "../../css/View.css"
import {getBooks} from "../../Service/BookService";
import Search from "antd/es/input/Search";

export class HomePage extends React.Component {
    constructor(props) {
        super(props);
        // this.state = { books: booksData };
        this.state = { books: [],
            searchBooks: [],
            searching: false,
        };
    }
    async componentDidMount() {
        const books = await getBooks();
        this.setState({ books, searchBooks: books });
    }

    render = () => {
        return (
            <Layout style={{backgroundColor:'transparent'}}>
                <Content >
                    <div style={{ display: 'flex', flexDirection:'column',alignItems:"center"}}>
                        <Search
                            placeholder="输入书名或作者"
                            allowClear
                            enterButton="搜索"
                            size="large"
                            style={{width:"75%", margin:"20px"}}
                            onSearch={async (value) => {
                                if(value === "") {
                                    this.setState({searchBooks: this.state.books});
                                    this.setState({searching: false})
                                    return;
                                }
                                this.setState({searching: true})
                                const searchBooks = this.state.books.filter((book) => {
                                    return book.title.includes(value) || book.author.includes(value);
                                });
                                this.setState({searchBooks});
                                console.log("searchBooks: ", searchBooks);
                            }}
                        />
                        {!this.state.searching && <div style={{width: '60%'}}>
                            <BookCarousel/>
                        </div>}

                        <BookList books={this.state.searchBooks}/>
                        <div className={"foot-wrapper"}>

                        </div>
                    </div>
                </Content>
            </Layout>
        );

    };
};
export default HomePage;