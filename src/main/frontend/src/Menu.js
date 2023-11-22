import React, { useState } from 'react';
import ImageUploader from './ImageUploader';
import ImageList from './ImageList';

const Menu = () => {
    const [uploaded, setUploaded] = useState(false);
    const [imageListKey, setImageListKey] = useState(1);

    const handleUpload = () => {
        setUploaded(!uploaded);
        // ImageList 컴포넌트를 다시 로드하기 위해 key 값을 변경
        setImageListKey((prevKey) => prevKey + 1);
    };

    return (
        <div>
            <header><h1>메뉴 등록</h1></header>


            <ImageUploader onUpload={handleUpload} />
            <ImageList key={imageListKey} />
        </div>
    );
};
export default Menu;