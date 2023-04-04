import React from "react";
import {Content, Header} from "antd/es/layout/layout";
import {Carousel, Layout} from "antd";
import {SearchBar} from "../Component/SearchBar";
import BookCarousel from "../Component/BookCarousel";
import {BookList} from "../Component/BookList";

export class HomePage extends React.Component {
    render = () => {
        return (
            <Layout >
                <Content >
                    <div className="home-content" style={{ display: 'flex', flexDirection:'column',alignItems:"center"}}>
                        <SearchBar />
                        <div style={{ width:'60%' }} >
                            <BookCarousel />
                        </div>

                        <BookList />
                        <div className={"foot-wrapper"}>

                        </div>
                    </div>
                </Content>
            </Layout>
        );

    };
};
export default HomePage;