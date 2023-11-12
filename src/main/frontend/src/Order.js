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

    const sendMessage = (menu, store) => {
        stomp.send('/app/chat', {},
            JSON.stringify({menu: menu, store: store}));
            showAlert();
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
            <header className="header">
                <h1>Smart Order System</h1>
            </header>

            <main className="main">
                <nav className="nav">
                    <button>전체</button>
                    <button>한식</button>
                    <button>안주</button>
                    <button>주류</button>
                </nav>

                <div className="menu-container">
                    <div className="menu-row">
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=감자튀김"/>
                            <h2>감자튀김</h2>
                            <p>가격: 10,000</p>
                            <button className="order-button" onClick={()=>sendMessage("감자튀김", "test")}>주문하기</button>
                        </section>
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=곱창전골" />
                            <h2>곱창전골</h2>
                            <p>가격: 24,000</p>
                            <button className="order-button" onClick={()=>sendMessage("곱창전골", "test")}>주문하기</button>
                        </section>
                    </div>
                    <div className="menu-row">
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=김치찌개" />
                            <h2>김치찌개</h2>
                            <p>가격: 15,000</p>
                            <button className="order-button" onClick={()=>sendMessage("김치찌개", "test")}>주문하기</button>
                        </section>
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=떡볶이"/>
                            <h2>떡볶이</h2>
                            <p>가격: 18,000</p>
                            <button className="order-button" onClick={()=>sendMessage("떡볶이", "test")}>주문하기</button>
                        </section>
                    </div>
                    <div className="menu-row">
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=오뎅탕" />
                            <h2>오뎅탕</h2>
                            <p>가격: 14,000</p>
                            <button className="order-button" onClick={()=>sendMessage("오뎅탕", "test")}>주문하기</button>
                        </section>
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=후라이드치킨"/>
                            <h2>후라이드치킨</h2>
                            <p>가격: 20,000</p>
                            <button className="order-button" onClick={()=>sendMessage("후라이드치킨", "test")}>주문하기</button>
                        </section>
                    </div>
                    <div className="menu-row">
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=생맥주"/>
                            <h2>맥주</h2>
                            <p>가격: 5,000</p>
                            <button className="order-button" onClick={()=>sendMessage("생맥주", "test")}>주문하기</button>
                        </section>
                        <section className="menu">
                            <img src="/api/order/image?store=test&menu=진로" />
                            <h2>소주</h2>
                            <p>가격: 5,000</p>
                            <button className="order-button" onClick={()=>sendMessage("진로", "test")}>주문하기</button>
                        </section>
                    </div>
                </div>
            </main>

            <footer>
                <p>주소 : 서울특별시 마포구 와우산로 94 홍익대학교 | 전화번호 : 010-2803-1960 | 대표 : 이재진</p>
            </footer>

        </div>

    );

}

export default Order;