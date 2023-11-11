import React from 'react';
import axios from 'axios';
import './Table.css';
import { useNavigate } from "react-router-dom";
import ImageComponent from "./ImageComponent";

function Table() {

    const movePage = useNavigate();
    function gohome(){
        movePage('/');
    }

    function goOrder(){
        movePage('/order');
    }
    function goCounter(){
        movePage('/counter');
    }
    const handlePrint = () => {
        window.print();
    };
    return(
        <div id="table">
            <div id = "gohome">
                <button onClick={gohome}>홈으로</button>
                <button onClick={goCounter}>카운터로</button>
            </div>
            <h1>(가게이름) QR코드 등록</h1>
            <h2>테이블 이름 입력</h2>
        <ImageComponent />

            <div id="qrCodeContainer"></div>
            <button id = "printButton" onClick={handlePrint}>페이지 인쇄</button>
        </div>

    )

}
export default Table;
