package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    public void uploadImage(MultipartFile file);
    public List<String> getImageList();
}
