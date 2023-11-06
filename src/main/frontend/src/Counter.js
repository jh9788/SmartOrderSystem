import './Counter.css';
import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import SockJs from "sockjs-client";
import StompJs from "stompjs";
import Order from "./Order";


function Counter(){


    const sock = new SockJs("/chat");
//client 객체 생성 및 서버주소 입력

    const stomp = StompJs.over(sock);
//stomp로 감싸기

    const stompConnect = (() => {
        //stomp.debug = null;
        stomp.connect({}, () => {
            stomp.subscribe(
                `/topic/messages`,
                (data) => {
                    const newMessage = JSON.parse(data.body);
                    //데이터 파싱
                    setMessage(newMessage);
                }
            );
        });
    });

    const setMessage = ((message) =>{
        const response = document.getElementById('response');
        const p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode("주문 메뉴: " +message.menu + "  테이블: 1번" + "  수량: "
            + message.quantity + "  시간: " + message.time));
        response.appendChild(p).className = "orders";
    });
//웹소켓 connect-subscribe 부분

    // const stompDisConnect = () => {
    //     try {
    //         stomp.debug = null;
    //         stomp.disconnect(() => {
    //             stomp.unsubscribe("sub-0");
    //         });
    //     } catch (err) {
    //
    //     }
    // };
//웹소켓 disconnect-unsubscribe 부분
// 웹소켓을 disconnect을 따로 해주지 않으면 계속 연결되어 있어서 사용하지 않을때는 꼭 연결을 끊어주어야한다.

    // const SendMessage = (menu, store) => {
    //     stomp.send('/app/chat', {},
    //         JSON.stringify({menu: menu, store: store}));
    // };

    //예시 - 데이터 보낼때 json형식을 맞추어 보낸다.


    useEffect(() => {
        stompConnect();
    }, []);

    function showAlert() {
        alert("주문이 완료되었습니다!");
    }


    return (
        <div className="App">
            <header>
                <h1>역전승환이맥주</h1>
            </header>

            <div className="wrapper">

                <div className="chat-messages" >
                    <p id="response"></p>
                </div>
            </div>
            <footer>
                <p>주소 : 서울특별시 마포구 와우산로 94 홍익대학교 | 전화번호 : 010-2803-1960 | 대표 : 이재진</p>
            </footer>

        </div>

    );


}
export default Counter;