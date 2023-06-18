import React from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
function Login() {

    const movePage = useNavigate();
    function gohome(){
        movePage('/Home');
    }

    return(
        <div id="login">
            login 입니다.
            <div id = "gohome">
                홈 입니다.
                첫 페이지
                <button onClick={gohome}>홈으로이동</button>
            </div>

        </div>




    )

}

export default Login;
