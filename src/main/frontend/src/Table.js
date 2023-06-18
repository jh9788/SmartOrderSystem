import React from 'react';
import axios from 'axios';
import './Table.css';
import { useNavigate } from "react-router-dom";
import ImageComponent from "./ImageComponent";

function Table() {

    const movePage = useNavigate();
    function gohome(){
        movePage('/Home');
    }
    const handlePrint = () => {
        window.print();
    };
    return(
        <div id="table">
            table 입니다.
            <div id = "gohome">
                <button onClick={gohome}>홈으로이동</button>
            </div>
            <h1>역전승환이맥주 QR코드</h1>
        <ImageComponent />

            <div id="qrCodeContainer"></div>
            <button id = "printButton" onClick={handlePrint}>페이지 인쇄</button>
        </div>

    )

}
export default Table;
