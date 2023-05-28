
import User from '../View/User'

import {
    HeartOutlined,
    ShoppingCartOutlined,
    BookOutlined,
    HomeOutlined,
    FileTextOutlined,
    SmileOutlined
} from '@ant-design/icons'
import ManageOrderView from "../View/Admin/ManageOrderView";

const getAdminRoutes = () => [
    {
        path: '/',
        element: <User />,
        icon: <SmileOutlined />,
        label: '主页',
        hideInMenu:false,
    },
    {
        path: '/manage',
        label: '管理订单',
        element: <ManageOrderView />,
        icon: <FileTextOutlined />,
        hideInMenu:false,
    },
];



export default getAdminRoutes;