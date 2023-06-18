
import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

function Join() {

    const movePage = useNavigate();
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [gender, setGender] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();


    }
    const formData = {
        name: name,
        username: username,
        password: password,
        email: email,
        phone: phone,
        gender: gender
    };
    function gohome(){
        movePage('/Home');

    axios.post('/api/owner', formData)
            .then(function(response) {
                console.log(response.data);
                alert("회원가입이 완료되었습니다!");
                window.location.href = "메인화면.html";
            })
            .catch(function(error) {
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
                    <label htmlFor="username">아이디:</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
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
                    <label htmlFor="phone">휴대폰 번호:</label>
                    <input
                        type="text"
                        id="phone"
                        name="phone"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
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
                <button type="submit">가입하기</button>
            </form>




            <div id = "gohome">
                <button onClick={gohome}>홈으로이동</button>
            </div>

        </div>

    )

}
export default Join;
