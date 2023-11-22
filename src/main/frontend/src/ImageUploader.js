import React, { useState } from 'react';
import axios from 'axios';

const ImageUploader = ({ onUpload }) => {
    const handleFileChange = (e) => {
        const file = e.target.files[0];
        const formData = new FormData();
        formData.append('file', file);

        axios.post('/api/menu/images/upload', formData)
            .then(() => {
                // 이미지 업로드 성공 시 부모 컴포넌트에 알림
                onUpload();
            })
            .catch(error => {
                console.error('Error uploading image:', error);
                // 에러 처리
            });
    };


    return (
        <div>
            <form>
            <h2>메뉴 이름</h2>
            <input type = "text"/>
            <h2>메뉴 사진</h2>
            <input type="file" onChange={handleFileChange}/>
                <button>
                    등록하기
                </button>
            </form>
        </div>
    );
};

export default ImageUploader;