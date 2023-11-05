import './Order.css';
import React from 'react';
import axios from 'axios';
import { useEffect, useState } from "react";
import SockJs from "sockjs-client";
import StompJs from "stompjs";

function Order() {

    const items = document.querySelectorAll(".menu-item");
    const menuTypeButtons = document.querySelectorAll(".menu-type-button");


    //const sock = new SockJs("http://localhost:8080/chat");
    const sock = new SockJs("/chat");
//client 객체 생성 및 서버주소 입력

    const stomp = StompJs.over(sock);
//stomp로 감싸기

    const stompConnect = (() => {

            stomp.connect({}, () => {
                stomp.subscribe(
                    `/topic/messages`,
                    (data) => {
                        const newMessage = JSON.parse(data.body);
                        //데이터 파싱
                        //todo setMessage 함수 넣기
                    }
                );
            });
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

    const SendMessage = (menu, store) => {
        stomp.send('/app/chat', {},
            JSON.stringify({menu: menu, store: store}));
        };

        //예시 - 데이터 보낼때 json형식을 맞추어 보낸다.


    useEffect(() => {
        stompConnect();
    }, []);

    function showAlert() {
        alert("주문이 완료되었습니다!");
    }


    /*구분-------------------------------------------*/
    function handleOrderClick(menuName, menuPrice){
        axios.post('/api/order', {name: menuName, price: menuPrice})
            .then((response) => {
                console.log(response);
            })
            .catch((error)=> {
                console.log(error);
            });
        //자기전에 json으로 보내는 코드.. 좀달랐음..
    }

    return (
        <div className="App">
            <header>
                <h1>비대면 주문 시스템</h1>
            </header>

            <div className ="menu-types">
                <button className ="menu-type-button active" data-menu-type="all">전체</button>
                <button className ="menu-type-button" data-menu-type="korean">한식</button>
                <button className ="menu-type-button" data-menu-type="side-dish">안주</button>
                <button className ="menu-type-button" data-menu-type="drink">주류</button>
                <button className ="menu-type-button" data-menu-type="message" onClick={() =>
                    SendMessage('곱창전골', '역전승환이맥주')}>메시지전송</button>
            </div>

            <div className="wrapper">

                <div className="menu-item korean">
                    <img src="api/order/image?store=test&menu=곱창전골" alt="곱창전골">
                    </img>
                    <h3>곱창전골</h3>
                    <p>곱이 꽉 찬 소곱창전골입니다. </p>
                    <p>가격 : 30000원</p>
                    <button onClick={()=>handleOrderClick('곱창전골',30000)}>주문하기</button>
                </div>
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

export default Order;