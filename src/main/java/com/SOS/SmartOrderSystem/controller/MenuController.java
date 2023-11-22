package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.repository.jpa.JpaMenuRepository;
import com.SOS.SmartOrderSystem.service.MenuService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final JpaMenuRepository menuRepository;
    private final MenuService menuService;

    @Autowired
    public MenuController(JpaMenuRepository menuRepository, MenuService menuService) {
        this.menuRepository = menuRepository;
        this.menuService = menuService;
    }

    @PostConstruct
    public void init() {
        Menu menu1 = new Menu(1, "곱창전골", 30000, 1, "/img/곱창전골.jpg");
        Menu menu2 = new Menu(2, "후라이드치킨", 20000, 1, "/img/후라이드치킨.jpg");
        Menu menu3 = new Menu(3, "떡볶이", 18000, 1, "/img/떡볶이.jpg");
        Menu menu4 = new Menu(4, "김치찌개", 18000, 1, "/img/김치찌개.jpg");
        Menu menu5 = new Menu(5, "오뎅탕", 13000, 1, "/img/오뎅탕.jpg");
        Menu menu6 = new Menu(6, "감자튀김", 10000, 1, "/img/감자튀김.jpg");
        Menu menu7 = new Menu(7, "생맥주", 5000, 1, "/img/생맥주.jpg");
        Menu menu8 = new Menu(8, "소주", 5000, 1, "/img/소주.jpg");
        Menu menu9 = new Menu(9, "매화수", 6000, 1, "/img/매화수.jpg");

        this.menuRepository.save(menu1);
        this.menuRepository.save(menu2);
        this.menuRepository.save(menu3);
        this.menuRepository.save(menu4);
        this.menuRepository.save(menu5);
        this.menuRepository.save(menu6);
        this.menuRepository.save(menu7);
        this.menuRepository.save(menu8);
        this.menuRepository.save(menu9);
    }

    // FTP 서버 정보
    //@Value("${ftp.server.host}")
    @Value("jh9788.ipdisk.co.kr")
    private String server;

    //@Value("${ftp.server.port}")
    @Value("21")
    private int port;

    //@Value("${ftp.server.username}")
    @Value("jh9788")
    private String username;

    //@Value("${ftp.server.password}")
    @Value("jonghun3127")
    private String password;

    //@Value("${ftp.server.upload.dir}")
    @Value("/HDD1/project-image/user-test")
    private String uploadDirectory;


    @GetMapping("/images/test")
    public ResponseEntity<ByteArrayResource> getTest() {
        try {
            return getImage("main.png");
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/images/list")
    public ResponseEntity<List<String>> getImageList() throws IOException {
        List<String> imageList = getFtpImageList();
        Collections.sort(imageList);

        return ResponseEntity.ok(imageList);
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String imageName) throws IOException {

        byte[] imageBytes = downloadImage(imageName);
        //System.out.println("imageBytes.length = " + imageBytes.length);
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        // 파일명을 UTF-8로 인코딩
       // String encodedFileName = URLEncoder.encode(imageName, "UTF-8").replace("+", "%20");

        return ResponseEntity.ok()
                .body(resource);

    }

    private List<String> getFtpImageList() throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(server, port);
            ftpClient.login(username, password);

            // FTP 서버의 디렉토리에서 이미지 목록을 가져옴
            return Arrays.stream(ftpClient.listFiles(uploadDirectory))
                    .map(FTPFile::getName)
                    .collect(Collectors.toList());
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
    }

    private byte[] downloadImage(String imageName) throws IOException {
        FTPClient ftpClient = new FTPClient();
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ftpClient.setControlEncoding("UTF-8"); // 얘가 connect 앞에 있어야 한글 오류가 안남!
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // FTP 서버에서 이미지 다운로드
            ftpClient.retrieveFile(uploadDirectory + "/" + imageName, output);
           // System.out.println("output.size() = " + output.size());
            return output.toByteArray();
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }
    }

    @PostMapping("/images/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        FTPClient ftpClient = new FTPClient();
        try (InputStream input = file.getInputStream()) {
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(server, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // FTP 서버에 이미지 업로드
            ftpClient.storeFile(uploadDirectory + "/" + file.getOriginalFilename(), input);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }


    }
}
