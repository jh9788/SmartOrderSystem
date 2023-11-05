import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Main from './Main';
import Login from './Login';
import Table from './Table';
import Order from './Order';
import Join from './Join';
import Counter from './Counter'
import React from "react";

function App(){

    return(
        <BrowserRouter>
            <Routes>
                <Route path = "/" element = {<Main/>} />
                <Route path = "/login" element = {<Login/>} />
                <Route path = "/table" element = {<Table/>} />
                <Route path = "/order" element = {<Order/>}/>
                <Route path = "/join" element = {<Join/>}/>
                <Route path = "/counter" element = {<Counter/>}/>
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