import React from 'react';
import "../css/login.css"
import LoginForm from "../Component/LoginForm";

import {Layout} from "antd";

class LoginView extends React.Component {

render() {
        const props = this.props;
        const UserData = props.UserData;
        return (
            <Layout className="login-view" >
                <h1>Login</h1>
                <div>
                    <LoginForm UserData={UserData} onLogin={props.onLogin} />
                </div>
            </Layout>
        );
    }
}

export default LoginView;