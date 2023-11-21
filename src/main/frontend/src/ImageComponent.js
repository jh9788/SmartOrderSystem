import React, { useState, useEffect } from 'react';

function ImageComponent() {
    const [imageData, setImageData] = useState(null);
    const [inputUrl, setInputUrl] = useState('');

    function getQR() {
        //fetch(`http://localhost:3000/qr?url=${encodeURIComponent(inputUrl)}`)
        fetch(`/qr?url=${encodeURIComponent(inputUrl)}`)
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
        <div>
            <div id="getQR">
                <input type="text" name="url" value={inputUrl} onChange={e => setInputUrl(e.target.value)} />
                <button onClick={getQR}>QR 생성으로 이동</button>
            </div>
            {imageData && <img src={imageData} alt="QR Code" />}
        </div>
    );
}


export default ImageComponent;