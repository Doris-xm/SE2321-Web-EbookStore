import React from 'react';
import { Row, Col } from 'antd';
import '../../css/index.css'
import logo from '../../asset/logo.svg';
import {UserInfo} from "./UserInfo";
import {Notification} from "../Notice/Notification";



export class HeaderInfo extends React.Component {

    render(){

        return(
            <div id="header"  >
                <div id="header-content">
                    <Row style={{backgroundColor:'transparent'}} >
                        <Col xs={24} sm={24} md={5} lg={5} xl={5} xxl={4}>
                            <a id="logo" href={"/"}>
                                <img alt="logo"  className="logo" src={logo} style={{ height:45 }}/>
                            </a>
                        </Col>
                        <Col xs={0} sm={0} md={10} lg={10} xl={10} xxl={10} />
                        <Col xs={0} sm={0} md={3} lg={3} xl={3} xxl={3} >
                            <UserInfo />
                        </Col>
                        <Col xs={5} sm={0} md={3} lg={3} xl={3} xxl={3} >
                            <Notification />
                        </Col>
                    </Row>
                </div>
            </div>
        );
    }
}