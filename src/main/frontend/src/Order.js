import './Order.css';
import React from 'react';
import axios from 'axios';

function Order() {

    const items = document.querySelectorAll(".menu-item");
    const menuTypeButtons = document.querySelectorAll(".menu-type-button");


    function showAlert() {
        alert("주문이 완료되었습니다!");
    }

    items.forEach(item => {
        const button = item.querySelector("button");
        button.onclick = showAlert;
    });

    menuTypeButtons.forEach(button => {
        button.addEventListener("click", () => {
            // 현재 클릭된 버튼에 active 클래스 추가
            button.classList.add("active");

            // 다른 버튼에서 active 클래스 제거
            menuTypeButtons.forEach(otherButton => {
                if (otherButton !== button) {
                    otherButton.classList.remove("active");
                }
            });

            //해당 메뉴 종류에 해당하는 메뉴만 보여주도록 변경
            const menuType = button.dataset.menuType;

            items.forEach(item => {
                if (menuType === "all" || item.classList.contains(menuType)) {
                    item.style.display = "block";
                } else {
                    item.style.display = "none";
                }
            });
        });
    });
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
            </div>

            <div className="wrapper">

                <div className="menu-item korean">
                    <img src="./img/곱창전골.jpg" alt="곱창전골">
                    </img>
                    <h3>곱창전골</h3>
                    <p>곱이 꽉 찬 소곱창전골입니다. </p>
                    <p>가격 : 30000원</p>
                    <button onClick={()=>handleOrderClick('곱창전골',30000)}>주문하기</button>
                </div>

                <div className="menu-item side-dish">
                    <img src="./img/후라이드치킨.jpg" alt="후라이드치킨"></img>
                    <h3>후라이드치킨</h3>
                    <p>BBQ, BHC를 능가하는 바삭함</p>
                    <p>가격 : 20000원</p>
                    <button onClick={()=>handleOrderClick('후라이드치킨',20000)}>주문하기</button>
                </div>

                <div className="menu-item korean">
                    <img src="./img/떡볶이.jpg" alt="떡볶이"></img>
                    <h3>떡볶이</h3>
                    <p>순한맛/중간맛/매운맛/사망맛 골라주세요</p>
                    <p>가격 : 18000원</p>
                    <button onClick={()=>handleOrderClick('떡볶이',18000)}>주문하기</button>
                </div>

                <div className="menu-item korean">
                    <img src="./img/김치찌개.jpg" alt="김치찌개"></img>
                    <h3>김치찌개</h3>
                    <p>돼지고기가 들어간 김치찌개</p>
                    <p>가격 : 18000원</p>
                    <button onClick={()=>handleOrderClick('김치찌개',18000)}>주문하기</button>
                </div>

                <div className="menu-item side-dish">
                    <img src="./img/오뎅탕.jpg" alt="오뎅탕"></img>
                    <h3>오뎅탕</h3>
                    <p>결정장애 왔을 때 최고의 안주</p>
                    <p>가격 : 13000원</p>
                    <button onClick={()=>handleOrderClick('오뎅탕',13000)}>주문하기</button>
                </div>
                <div className="menu-item side-dish">
                    <img src="./img/감자튀김.jpg" alt="감자튀김"></img>
                    <h3>감자튀김</h3>
                    <p>맘스터치 감자튀김. 내가가르침</p>
                    <p>가격 : 10000원</p>
                    <button onClick={()=>handleOrderClick('감자튀김',10000)}>주문하기</button>
                </div>

                <div className="menu-item drink">
                    <img src="./img/생맥주.jpg" alt="생맥주"></img>
                    <h3>생맥주 500CC</h3>
                    <p>아사이 500cc 생맥주</p>
                    <p>가격 : 5000원</p>
                    <button onClick={()=>handleOrderClick('생맥주',5000)}>주문하기</button>
                </div>

                <div className="menu-item drink">
                    <img src="./img/진로.jpg" alt="진로소주"></img>
                    <h3>소주</h3>
                    <p>진로/참이슬</p>
                    <p>가격 : 5000원</p>
                    <button onClick={()=>handleOrderClick('소주',5000)}>주문하기</button>
                </div>


                <div className="menu-item drink">
                    <img src="./img/매화수.jpg" alt="매화수"></img>
                    <h3>매화수</h3>
                    <p>달콤한 매화수/숙취 조심</p>
                    <p>가격 : 6000원</p>
                    <button onClick={()=>handleOrderClick('매화수',6000)}>주문하기</button>
                </div>
            </div>
            <footer>
                <p>주소 : 서울특별시 마포구 와우산로 94 홍익대학교 | 전화번호 : 010-2803-1960 | 대표 : 이재진</p>
            </footer>

        </div>

    );

}

export default Order;