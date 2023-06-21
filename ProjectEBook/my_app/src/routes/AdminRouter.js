
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
import ManageUserView from "../View/Admin/ManageUserView";
import ManageBookView from "../View/Admin/ManageBookView";
import SalesView from "../View/Admin/SalesView";


const getAdminRoutes = () => [
    {
        path: '/manageBook',
        label: '管理书籍',
        element: <ManageBookView />,
        icon: <BookOutlined />,
        hideInMenu:false,
    },
    {
        path: '/manageUser',
        label: '管理用户',
        element: <ManageUserView />,
        icon: <SmileOutlined />,
        hideInMenu:false,
    },
    {
        path: '/manageOrder',
        label: '管理订单',
        element: <ManageOrderView />,
        icon: <FileTextOutlined />,
        hideInMenu:false,
    },
    {
        path: '/mangeSales',
        label: '销量统计',
        element: <SalesView />,
        icon: <FileTextOutlined />,
        hideInMenu:false,
    },

];



export default getAdminRoutes;