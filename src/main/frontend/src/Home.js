import React from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import './Home.css';

function Home() {
    const movePage = useNavigate();
    function goLogin(){
        movePage('/Login');
    }
    function goJoin(){
        movePage('/Join');
    }

    return(

            <div id="login">
                <div id="goLogin">
                    <h2>Smart Order System</h2>
                    환영합니다<br/>


                        <button onClick={goLogin}>로그인</button>
                    <button onClick={goJoin}>회원가입</button>
                </div>
            </div>
    )

}

export default Home;