import {Layout, Menu} from "antd";
import {HeaderInfo} from "../../Component/HeaderInfo";
import React  from "react";
import {Content, Header} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import {Link, useLocation, useRoutes} from "react-router-dom";
// import "../../css/View.css"
import getAdminRoutes from "../../routes/AdminRouter";


const AdminView = () => {

    // Menu组件通过指定items属性，进行菜单渲染
    const location = useLocation();
    const items = [];
    const routes = getAdminRoutes();
    const element = useRoutes(routes)
    // 对路由规则数组进行遍历，并对其进行改造，改造成与Menu的items属性相同的结构
    routes.forEach((item) => {
        // if (item.hideInMenu) {
        //     return; // 如果设置了 hideInMenu 属性，则跳过该路由规则
        // }
        items.push({
            label: <Link to={item.path}>{item.label}</Link>,
            key: item.path,
            icon: item.icon,
        })
    })

    return (
        <>
            <Layout className={'background'} >
                <Header className={'glass-container'}>
                    <HeaderInfo />
                </Header>
                <Layout style={{ display: 'flex',backgroundColor: 'transparent' }}>
                    <Sider
                        style={{ width: '5%', flex: 'none',backgroundColor: 'transparent' }}>
                        <Menu className={'glass-container'} mode="inline" items={items}
                              selectedKeys={[location.pathname]} >
                        </Menu>
                    </Sider>

                    {/*<Content style={{flex: '1' }} >*/}
                    <Content style={{flex: '1' ,backgroundColor: 'transparent' }}>
                        {element}
                    </Content>
                </Layout>

            </Layout>

        </>
    )
};

export default AdminView;