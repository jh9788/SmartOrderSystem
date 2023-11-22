package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class MenuServiceImpl implements MenuService{

    private List<String> imageList = new ArrayList<>();

    public void uploadImage(MultipartFile file) {
        // 이미지를 저장하거나 다른 처리를 수행합니다.
        // 여기서는 간단하게 이미지 파일 이름만 저장합니다.
        //try {
            String fileName = file.getOriginalFilename();
            imageList.add(fileName);
        //} catch (IOException e) {
            //e.printStackTrace();
            // 예외 처리
        //}
    }

    public List<String> getImageList() {
        // 이미지 목록을 반환합니다.
        return imageList;
    }
}
