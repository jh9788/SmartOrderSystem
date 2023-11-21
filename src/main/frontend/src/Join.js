import axios from 'axios';
import './Join.css';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

function Join() {

    const movePage = useNavigate();
    const [name, setName] = useState('');
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [passwordCheck, setPasswordCheck] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [gender, setGender] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();


    }
    const formData = {
        name: name,
        id: id,
        password: password,
        email: email,
        phoneNumber: phoneNumber,
        gender: gender
    };
    function goMain(){
        movePage('/');

    }

    function handleOrderClick(name, id, password, passwordCheck, email, phoneNumber, gender) {
        axios.post('/api/join', formData)
            .then(function (response) {
                console.log(response.data);
                alert("회원가입이 완료되었습니다!");
                movePage('/');
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    return(
        <div id="join">
            <div id="head">
            <h1>Smart Order System</h1>
            </div>

            <div id = "go-main">
                <button onClick={goMain}>홈으로</button>
            </div>



            <div id="title">
                <h1>Sign Up</h1>
            </div>

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">이름:</label>
                    <input
                        type="text"
                        id="name"
                        name="name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                        placeholder="Name"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="id">아이디:</label>
                    <input
                        type="text"
                        id="id"
                        name="id"
                        value={id}
                        onChange={(e) => setId(e.target.value)}
                        required
                        placeholder="ID"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">비밀번호:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        placeholder="Password"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="passwordCheck">비밀번호 확인:</label>
                    <input
                        type="passwordCheck"
                        id="passwordCheck"
                        name="passwordCheck"
                        value={passwordCheck}
                        onChange={(e) => setPasswordCheck(e.target.value)}
                        required
                        placeholder="PasswordCheck"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">이메일:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        placeholder="E-Mail"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="phoneNumber">휴대폰 번호:</label>
                    <input
                        type="text"
                        id="phoneNumber"
                        name="phoneNumber"
                        value={phoneNumber}
                        onChange={(e) => setPhoneNumber(e.target.value)}
                        required
                        placeholder="phone"
                    />
                </div>
                <div className="form-group">
                    <label>성별:</label>
                    <label>
                        <input
                            type="checkbox"
                            name="gender"
                            value="male"
                            checked={gender === "male"}
                            onChange={(e) => setGender(e.target.value)}
                        /> 남성
                    </label>
                    <label>
                        <input
                            type="checkbox"
                            name="gender"
                            value="female"
                            checked={gender === "female"}
                            onChange={(e) => setGender(e.target.value)}
                        /> 여성
                    </label>
                </div>
                <div id="join-button">
                <button onClick={()=>handleOrderClick(name, id, password, email, phoneNumber, gender)}>가입하기</button>
                </div>
            </form>
            <div id="company-info">
                대표: 하종훈/ 주소: 서울특별시 마포구 와우산로 94 홍익대학교
            </div>





        </div>

    )

}
export default Join;
