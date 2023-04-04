import React from 'react';
import { Layout, theme } from 'antd';
import SideBar from "./sideBar";
import HeaderInfo from "../components/headerInfo";
import '../css/home.css'
import BookList from "../components/bookList";

const { Header, Content, Sider, Footer } = Layout;

const {books} = [];

const Home: React.FC = () => {
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    return (
        <Layout className="layout">
            <Header>
                <HeaderInfo />
            </Header>
            <Layout>
                <Sider width={200}
                       style={{
                           background: colorBgContainer,
                       }}>
                    <SideBar />
                </Sider>
                <Content style={{ padding: '0 50px' }}>
                    <BookList data = {books}/>
                </Content>
            </Layout>
            <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
    );
};

export default Home;