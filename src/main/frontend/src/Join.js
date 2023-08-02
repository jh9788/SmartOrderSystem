
import axios from 'axios';
import './Join.css';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

function Join() {

    const movePage = useNavigate();
    const [name, setName] = useState('');
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
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
        phone: phoneNumber,
        gender: gender
    };
    function gohome(){
        movePage('/Home');

    }

    function handleOrderClick(name, id, password, email, phoneNumber, gender) {
        axios.post('/api/join', formData)
            .then(function (response) {
                console.log(response.data);
                alert("회원가입이 완료되었습니다!");
                movePage('/Home');
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    return(
        <div id="join">
            <h1>회원가입</h1>
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
                <button onClick={()=>handleOrderClick(name, id, password, email, phoneNumber, gender)}>가입하기</button>
            </form>




            <div id = "gohome">
                <button onClick={gohome}>홈으로이동</button>
            </div>

        </div>

    )

}
export default Join;
