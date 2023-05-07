// export const checkUser =  async (name, password) => {
//     let user = null;
//     try {
//         const response = await fetch(`/user?name=${name}`, {
//             method: 'GET',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//         });
//         user = await response.json();
//         console.log("checkUser", user);
//     } catch (error) {
//         console.error("Error fetching books:", error);
//     }
//     if(user === null)
//         return 0;
//     if(user.password === password) {
//         const userJson = JSON.stringify(user);
//         localStorage.setItem('User', userJson);
//         return user.id;
//     }
//     return -1;
// };

import {message} from "antd";

export const checkUser =  async (name, password) => {
    // let user = null;
    const response = await  fetch('http://localhost:8001/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify([name, password]),
    });
       if (!response.ok) {
           const body = await response.json();
            console.log(body.err);
            message.error(body.err);
            return null;
            // throw new Error(response.json().toString());
        }
       const user = await response.json();
       console.log('login后端传来',user);
       message.success('欢迎你，'+name+'!');
       return user;
};

export const getUser = () => {
    const userJson = localStorage.getItem('User');
    const authToken = localStorage.getItem('authToken');
    console.log('authTOken',authToken)
    if (authToken) {
        return JSON.parse(userJson);
    }
    return null;
};
export const handleLogout = () => {
    localStorage.removeItem('authToken');
    localStorage.removeItem('User');
};