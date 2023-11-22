import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ImageList = () => {
    const [imageList, setImageList] = useState([]);

    const getImageList = () => {
        // 이미지 목록을 가져오는 요청
        axios.get('/api/menu/images/list')
            .then(response => {
                setImageList(response.data);
                console.log(imageList);
            })
            .catch(error => {
                console.error('Error fetching image list:', error);
                // 에러 처리
            });
    }
    useEffect(() => {

            getImageList();
    }, []);

    return (
        <div>
            <h2>이미지 목록</h2>
            <ul>
                {imageList.map((imageName, index) => (
                    <section key={index} className="menu">
                        <img src={`/api/menu/images/${imageName}`} alt={imageName} />
                        <h2>{imageName}</h2>
                        <p>가격: {}</p>
                        <button className="delete-button">
                            삭제하기
                        </button>
                    </section>
                ))}
            </ul>
        </div>
    );
};

export default ImageList;
