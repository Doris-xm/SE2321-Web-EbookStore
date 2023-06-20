import Excel, {AllBooksView} from '../View/User/AllBooksView'
import Cart from '../View/User/Cart'
import Order from '../View/User/Order'
import User from '../View/User'
import HomePage from '../View/User/Homepage'
import BookDetail from "../View/User/BookDetail";

import {
    HeartOutlined,
    ShoppingCartOutlined,
    BookOutlined,
    HomeOutlined,
    FileTextOutlined,
    SmileOutlined
} from '@ant-design/icons'

const getRoutes = () => [
    {
        path: '/',
        element: <HomePage />,
        icon: <HomeOutlined />,
        label: '首页',
        hideInMenu:false,
    },
    {
        path: '/book',
        element: <AllBooksView />,
        icon: <BookOutlined />,
        label: '所有书籍',
        hideInMenu:false,
    },
    {
        path: '/my',
        label: '我的',
        icon: <HeartOutlined />,
        hideInMenu:false,
        children: [
            {
                path: 'cart',
                element: <Cart />,
                label: '购物车',
                icon: <ShoppingCartOutlined />,
                hideInMenu:false,
            },
            {
                path: 'order',
                element: <Order/>,
                label: '订单',
                icon: <FileTextOutlined />,
                hideInMenu:false,
            },
            {
                path: 'info',
                element: <User />,
                label: '个人信息',
                icon: <SmileOutlined />,
                hideInMenu:false,
            },
        ],
    },
    {
        path: "/bookDetails/:bookId",
        label: '书籍详情',
        element: <BookDetail />,
        hideInMenu:true,
    },
];



export default getRoutes;