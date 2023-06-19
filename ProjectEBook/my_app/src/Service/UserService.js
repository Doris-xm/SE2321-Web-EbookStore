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
            message.success(data.msg + '欢迎你，' + data.data.nickname + '!');
            return data.data;
        }
    };
    return postRequest(url, data, callback);
};

export const getUser = async () => {
    const userJson = localStorage.getItem('User');
    console.log('userJson',userJson)
    const user = JSON.parse(userJson);
    if (user === null) {
        return null;
    }
    const url = '/api/userById';
    const data = {
        userId: user.id,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            localStorage.removeItem('User');
            return null;
        } else {
            return data.data;
        }
    };
    return postRequest(url, data, callback);
};
export const handleLogout = () => {
    const user  = JSON.parse(localStorage.getItem('User'));
    if(user === null) {
        return false;
    }
    const id = user.id;
    const url = `/api/logout`;
    const data = {
        userId: id,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            // message.error(data.msg);
            return false;
        } else {
            message.success(data.msg)
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

export const checkNewName = (name) => {
    // console.log('checkNewName',name);
    const url = `/api/checkName`;
    const data = {
        username: name,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            // console.log(data.msg);
            return false;
        } else {
            // console.log(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};
export const checkNewMail = (email) => {
    // console.log('checkNewName',name);
    const url = `/api/checkEmail`;
    const data = {
        mail: email,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            // console.log(data.msg);
            return false;
        } else {
            // console.log(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};
export const resignNewUser = (model) => {
    // console.log('checkNewName',name);
    const url = `/api/resign`;
    const data = {
        mail: model.email,
        username: model.name,
        password: model.pwd
    };
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
};

export const getAllUsers =()=>{
    const url = `/api/users`;
    const callback = (data) => {
        if (data.status <= 0) {
            // message.error(data.msg);
            return null;
        } else {
            // message.success(data.msg);
            return data.data.users;
        }
    };
    return postRequest(url, null, callback);
}

export const banUsers =(userId,isBan)=>{

    const url = `/api/banUser`;
    const data = {
        userId: userId,
        isBan: isBan,
    };
    const callback = (data) => {
        if (data.status <= 0) {
            message.error(data.msg);
            return false;
        } else {
            message.success(data.msg);
            return true;
        }
    };
    return postRequest(url, data, callback);
}