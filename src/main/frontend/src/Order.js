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
    const TabExample = () => {
        const [activeTab, setActiveTab] = useState('전체');

        const menuData = [
            { name: '감자튀김', price: 10000 },
            { name: '곱창전골', price: 24000 },
            { name: '김치찌개', price: 15000 },
            { name: '떡볶이', price: 18000 },
            { name: '오뎅탕', price: 14000 },
            { name: '후라이드치킨', price: 20000 },
        ];
        const menuData2 = [
            { name: '곱창전골', price: 24000 },
            { name: '김치찌개', price: 15000 },
            { name: '떡볶이', price: 18000 },
        ];
        const menuData3 = [
            { name: '감자튀김', price: 10000 },
            { name: '오뎅탕', price: 14000 },
            { name: '후라이드치킨', price: 20000 }
        ];
        const menuData4 = [
            { name: '맥주', price: 5000 },
            { name: '소주', price: 5000 },
        ];
        const handleTabClick = (tab) => {
            setActiveTab(tab);
        };

        return (
            <div>
                <div className="tab">
                    <button onClick={() => handleTabClick('전체')} className={activeTab === '전체' ? 'active' : ''}>
                        전체
                    </button>
                    <button onClick={() => handleTabClick('한식')} className={activeTab === '한식' ? 'active' : ''}>
                        한식
                    </button>
                    <button onClick={() => handleTabClick('안주')} className={activeTab === '안주' ? 'active' : ''}>
                        안주
                    </button>
                    <button onClick={() => handleTabClick('주류')} className={activeTab === '주류' ? 'active' : ''}>
                        주류
                    </button>
                </div>

                <div>

                    {activeTab === '전체' && menuData.map((menu, index) => (
                        <section key={index} className="menu">
                            <img src={`/api/order/image?store=test&menu=${menu.name}`} alt={menu.name} />
                            <h2>{menu.name}</h2>
                            <p>가격: {menu.price.toLocaleString()}</p>
                            <button className="order-button" onClick={() => sendMessage(menu.name, 'test')}>
                                주문하기
                            </button>
                        </section>
                    ))}
                    {activeTab === '한식' && menuData2.map((menu, index) => (
                        <section key={index} className="menu">
                            <img src={`/api/order/image?store=test&menu=${menu.name}`} alt={menu.name} />
                            <h2>{menu.name}</h2>
                            <p>가격: {menu.price.toLocaleString()}</p>
                            <button className="order-button" onClick={() => sendMessage(menu.name, 'test')}>
                                주문하기
                            </button>
                        </section>
                    ))}
                    {activeTab === '안주' && menuData3.map((menu, index) => (
                        <section key={index} className="menu">
                            <img src={`/api/order/image?store=test&menu=${menu.name}`} alt={menu.name} />
                            <h2>{menu.name}</h2>
                            <p>가격: {menu.price.toLocaleString()}</p>
                            <button className="order-button" onClick={() => sendMessage(menu.name, 'test')}>
                                주문하기
                            </button>
                        </section>
                    ))}
                    {activeTab === '주류' && menuData4.map((menu, index) => (
                        <section key={index} className="menu">
                            <img src={`/api/order/image?store=test&menu=${menu.name}`} alt={menu.name} />
                            <h2>{menu.name}</h2>
                            <p>가격: {menu.price.toLocaleString()}</p>
                            <button className="order-button" onClick={() => sendMessage(menu.name, 'test')}>
                                주문하기
                            </button>
                        </section>
                    ))}
                </div>
            </div>
        );
    };

        return (
            <div className="App">
                <header className="header">
                    <h1>Smart Order System</h1>
                </header>

                <TabExample />
                <footer>
                    <p>주소 : 서울특별시 마포구 와우산로 94 홍익대학교 | 전화번호 : 010-2803-1960 | 대표 : 이재진</p>
                </footer>

            </div>

        );

    }

export default Order;