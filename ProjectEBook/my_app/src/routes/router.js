import Excel from '../View/Excel'
import Cart from '../View/Cart'
import Order from '../View/Order'
import User from '../View/User'
import HomePage from '../View/Homepage'
import BookDetail from "../View/BookDetail";

import {
    HeartOutlined,
    ShoppingCartOutlined,
    BookOutlined,
    HomeOutlined,
    FileTextOutlined,
    UserOutlined, SmileOutlined
} from '@ant-design/icons'
const headers = ["Book", "Author", "Language", "Published", "Sales"];

const data = [["The Lord of the Rings", "J. R. R. Tolkien", "English", "1954-1955", "150 million"],
    ["Le Petit Prince (The Little Prince)", "Antoine de Saint-Exupéry", "French", "1943", "140 million"],
    ["Harry Potter and the Philosopher's Stone", "J. K. Rowling", "English", "1997", "107 million"],
    ["And Then There Were None", "Agatha Christie", "English", "1939", "100 million"],
    ["Dream of the Red Chamber", "Cao Xueqin", "Chinese", "1754-1791", "100 million"],
    ["The Hobbit", "J. R. R. Tolkien", "English", "1937", "100 million"],
    ["She: A History of Adventure", "H. Rider Haggard", "English", "1887", "100 million"],];


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
        element: <Excel headers={headers} initialData={data}/>,
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