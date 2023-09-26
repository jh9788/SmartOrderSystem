import React, { useState, useEffect } from 'react';

function LoadImageComponent() {
    const [imageData, setImageData] = useState(null);
    const [inputUrl, setInputUrl] = useState('');

    const url = 'http://localhost:3000'
    function getImg() {
        fetch(url + '?store=teststore&menu=testmenu')
            .then(response => response.blob())
            .then(data => {
                const reader = new FileReader();
                reader.onloadend = () => {
                    setImageData(reader.result);
                };
                reader.readAsDataURL(data);
            })
            .catch(error => console.log(error));
    }

    return (
        <span>
            <div id="getQR">
                <input type="text" name="url" value={inputUrl} onChange={e => setInputUrl(e.target.value)} />
                <button onClick={getQR}>QR 생성으로 이동</button>
            </div>
            {imageData && <img src={imageData} alt="QR Code" />}
        </span>
    );
}


export default LoadImageComponent;