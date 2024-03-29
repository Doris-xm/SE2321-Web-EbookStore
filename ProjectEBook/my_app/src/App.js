import React from 'react';
import { createRoot } from "react-dom/client";
import './css/Book.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import "./css/index.css";
import LoginView from "./View/LoginView";
import BasicView from "./View/User/BasicView";
import SelectView from "./View/SelectView";

const root = createRoot(document.getElementById("root"));

root.render(<App />);

function App() {

    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginView />}/>
                <Route path="/*" element={<SelectView />}/>
            </Routes>
        </Router>

    );
};

export default App;
