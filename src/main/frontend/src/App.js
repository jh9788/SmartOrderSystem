import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from './Home';
import Login from './Login';
import Table from './Table';
import Order from './Order';
import Join from './Join';
import React from "react";
function App(){

    return(
        <BrowserRouter>
            <Routes>
                <Route path = "/Home" element = {<Home/>} />
                <Route path = "/Login" element = {<Login/>} />
                <Route path = "/Table" element = {<Table/>} />
                <Route path = "/Order" element = {<Order/>}/>
                <Route path = "/Join" element = {<Join/>}/>
            </Routes>
            {/*
            <div id = "mainHome">
                <Link to="/Home">home 컴포넌트 띄우기</Link>
            </div>

            </div>
            <div id = "mainLogin">
                <Link to="/Login">login 컴포넌트 띄우기</Link>
            </div>
            <div id = "mainTable">
                <Link to="/Table">table 컴포넌트 띄우기</Link>
            </div>
            <div id = "mainOrder">
                <Link to="/Order">order 컴포넌트 띄우기</Link>
            </div>
            <div id = "mainJoin">
                <Link to="/Join">Join 컴포넌트 띄우기</Link>
            </div>
            */}

        </BrowserRouter>

    )
}
export default App