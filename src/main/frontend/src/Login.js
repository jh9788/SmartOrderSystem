import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import './Login.css';
function Login() {

    const movePage = useNavigate();
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');

    function gohome(){
        movePage('/Home');
    }
    function goTable(){
        movePage('/Table');
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        // Axios를 사용하여 백엔드로 데이터 보내기
        axios
            .post('/api/login', {
                id: id,
                password: password,
            })
            .then(function (response) {
                if(response.status === 200)
                {console.log("로그인 성공");
                    alert("로그인 성공");
                    goTable();
                }
                else if(response.status === 401) {
                    console.log("아이디나 비밀번호를 확인하세요");
                    alert("아이디나 비밀번호를 확인하세요");
                }
            })
            .catch(function (error) {
                console.error(error);
            });
    };

    return(
        <div id="login">
            <h1>로그인</h1>
            <form onSubmit={handleSubmit}>
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
                <button type="submit">로그인</button>
            </form>

        </div>
    )

}

export default Login;
