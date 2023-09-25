import axios, {HttpStatusCode} from 'axios';
import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import './Login.css';
function Login() {

    const movePage = useNavigate();
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');

    function goMain(){
        movePage('/main');
    }
    function goTable(){
        movePage('/table');
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
                else if(response.status === HttpStatusCode.Unauthorized  && response.status === HttpStatusCode.InternalServerError) {
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
            <div id="head">
            <h1>Smart Order System</h1>
            </div>
            <div id = "go-main">
                <button onClick={goMain}>홈으로</button>
            </div>
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
                <div id={"login-button"}>
                <button type="submit">로그인</button>
                </div>
            </form>
            <div id="company-info">
                대표: 하종훈/ 주소: 서울특별시 마포구 와우산로 94 홍익대학교
            </div>
        </div>
    )

}

export default Login;
