import React from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import './Main.css';

function Main() {
    const movePage = useNavigate();
    function goLogin(){
        movePage('/login');
    }
    function goJoin(){
        movePage('/join');
    }

    function goFaq(){
        movePage('/faq')
    }

    function goMenu(){
        movePage('/menu')
    }
    return (
        <div>
            <div className="e22_5">
                <div className="e22_7"></div>
                <div className="e22_20"></div>
                <span className="e22_291">대표: 하종훈/ 주소: 서울특별시 마포구 와우산로94</span>
                <div className="e22_15" onClick={goFaq}>Faq</div>
                <div className="e22_11" onClick={goMenu}>My Menu</div>
                <div className="e22_10" onClick={goJoin}>Sign up</div>
                <div className="e22_9" onClick={goLogin}>Login</div>
                <span className="e22_8">Smart Order System</span>
                <div className="e22_19"></div>
                <span className="e22_271">QR코드로 주문을 간편하게</span>
            </div>
        </div>
    );

}

export default Main;