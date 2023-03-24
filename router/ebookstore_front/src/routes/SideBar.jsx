import React from 'react';
import { BookTwoTone, ShoppingTwoTone, TabletTwoTone, SettingTwoTone } from '@ant-design/icons';
import type { MenuProps } from 'antd';
import { Menu } from 'antd';

type MenuItem = Required<MenuProps>['items'][number];

function getItem (
    label: React.ReactNode,
    key?: React.Key | null,
    icon?: React.ReactNode,
    children?: MenuItem[],
    type?: 'group',
): MenuItem {
    return {
        key,
        icon,
        children,
        label,
        type,
    }// as MenuItem;
}

const items: MenuItem[] = [
    getItem('Book List', 'sub1', <BookTwoTone />),
    getItem('My Shopping Cart', 'sub2', <ShoppingTwoTone />),
    getItem('My Orders', 'sub3', <TabletTwoTone />),
    getItem('My Profile', 'sub4', <SettingTwoTone />),
];

const onClick: MenuProps['onClick'] = (e) => {

};

const SideBar: React.FC = () => (
    <Menu onClick={onClick} style={{ width: 256 }} mode="vertical" items={items} />
);

export default SideBar;