import React, {useState} from 'react';
import { createRoot } from "react-dom/client";
import './css/Book.css';
import {BrowserRouter as Router, Routes, Route, Navigate, useRoutes, Link} from 'react-router-dom';
import "./css/index.css";
import {Col, Layout, Menu, Row} from 'antd'
import LoginView from "./View/LoginView";
import BasicView from "./View/BasicView";



const { Sider, Content, Header } = Layout;

const root = createRoot(document.getElementById("root"));

// export const UserData = [
//     {
//         id: 1,
//         online: false,
//         name: 'dxm',
//         password: 'dxm',
//         avatar: require('./asset/UserAvatar/User1.jpg'),
//         Use_year: 1,
//         bought: [1,2],
//     },
// ]
// const rootElement = document.getElementById("root");
root.render(<App />);

function App() {

    const [UserData, setUserData] = useState([
        {
            id: 1,
            online: false,
            name: 'dxm',
            password: 'dxm',
            avatar: require('./asset/UserAvatar/User1.jpg'),
            Use_year: 1,
            bought: [1, 2],
        },
    ]);

    function handleLogin(user) {
        setUserData((prevUserData) =>
            prevUserData.map((u) => (u.id === user.id ? { ...u, online: true } : u))
        );
    }


    return (
        // <Layout>
        //     <div style={{width: '100%', height:'100%'}}>
        //         {/*<BasicView user ={UserData.find((user) => user.id === 1)}/>*/}
        //         {element}
        //     </div>
        // </Layout>
        <Router>
            <Routes>
                {/*<Route path="/" element={<Navigate to="/login" />} />*/}
                <Route path="/login" element={<LoginView UserData = {UserData}  onLogin={handleLogin}/>}/>
                <Route path="/*" element={<BasicView  user = {UserData.find((user) => user.online === true)}  onLogin={handleLogin}/>}/>
            </Routes>
        </Router>

    );


};

export default App;
