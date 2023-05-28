import {message} from "antd";
import {postRequest} from "../util/ajax";

export const checkUser =  async (name, password) => {
    const url = '/api/login';
    const data = {
        username:name,
        password:password
    };
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return null;
        } else {
            message.success(data.msg + '欢迎你，' + name + '!');
            return data.data;
        }
    };
    return postRequest(url, data, callback);
};

export const getUser = () => {
    const userJson = localStorage.getItem('User');
    const authToken = localStorage.getItem('authToken');
    // console.log('authTOken',authToken)
    // if (authToken) {
        return JSON.parse(userJson);
    // }
    // return null;
};
export const handleLogout = () => {
    const user = getUser();
    if(user === null) {
        return false;
    }
    const id = getUser().id;
    const url = `/api/logout`;
    const data = {
        userId: id,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            // message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            localStorage.removeItem('authToken');
            localStorage.removeItem('User');
            return true;
        }
    };
    return postRequest(url, data, callback);
};

export const checkSession = (id) => {
    const url = `/api/checkSession`;
    const data = {
        userId: id,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            // message.error(data.msg);
            return false;
        } else {
            // message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};