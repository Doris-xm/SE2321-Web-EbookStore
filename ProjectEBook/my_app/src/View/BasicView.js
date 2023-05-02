import {Layout, Menu} from "antd";
import {HeaderInfo} from "../Component/HeaderInfo";
import React  from "react";
// import {UserData} from "../App";
import {Content, Header} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import {Link, useLocation, useRoutes} from "react-router-dom";
import getRoutes from '../routes/router';
import "../css/View.css"
// import {getUser} from "../Service/UserService";




const BasicView = () => {

    // Menu组件通过指定items属性，进行菜单渲染
    const location = useLocation();
    const items = [];
    const routes = getRoutes();
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const element = useRoutes(routes)
    // 对路由规则数组进行遍历，并对其进行改造，改造成与Menu的items属性相同的结构
    routes.forEach((item) => {
        if (item.hideInMenu) {
            return; // 如果设置了 hideInMenu 属性，则跳过该路由规则
        }
        items.push({
            label: <Link to={item.path}>{item.label}</Link>,
            key: item.path,
            icon: item.icon,
            children:
                item.children &&
                item.children.map((child) => {
                    return {
                        label: <Link to={item.path + '/' + child.path}>{child.label}</Link>,
                        key: item.path + '/' + child.path,
                        icon: child.icon,
                        children:
                            child.children &&
                            child.children.map((sun) => {
                                return {
                                    label: (
                                        <Link to={item.path + '/' + child.path + '/' + sun.path}>
                                            {sun.label}
                                        </Link>
                                    ),
                                    key: item.path + '/' + child.path + '/' + sun.path,
                                    icon: sun.icon,
                                }
                            }),
                    }
                }),
        })
    })

    return (
        <>
            <Layout>
                <Header >
                    <HeaderInfo />
                </Header>
                <Layout style={{ display: 'flex' }}>
                    <Sider theme="dark"
                           style={{ width: '5%', flex: 'none' }}>
                        <Menu theme="dark" mode="inline" items={items}
                              selectedKeys={[location.pathname]} >
                        </Menu>
                    </Sider>

                    {/*<Content style={{flex: '1' }} >*/}
                    <Content className="background">
                        {element}
                    </Content>
                </Layout>

            </Layout>

        </>
    )
};

export default BasicView;