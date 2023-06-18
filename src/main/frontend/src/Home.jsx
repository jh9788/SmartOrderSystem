
import React from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";


function Home() {
    const movePage = useNavigate();
    function goLogin(){
        movePage('/Login');
    }
    return(
        <div>
            <div id="login">
                login 입니다.
                <div id = "goLogin">
                    홈 입니다.
                    첫 페이지
                    <button onClick={goLogin}>로그인이동</button>
                </div>

            </div>

        </div>
    )

}

export default Home;
