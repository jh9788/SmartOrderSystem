package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.auth.JwtTokenProvider;
import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.service.OrderService;
import com.SOS.SmartOrderSystem.service.OwnerService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/qr")
public class TableRegisterController {
    // private static final String SECRET_KEY = "my-secret-key!@";
    // @Autowired private OwnerService ownerService;
    @GetMapping
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("url") String url) throws WriterException, IOException {

        int width = 500;
        int height = 500;



        System.out.println("url = " + url);
        String amazonUrl = "ec2-43-202-122-115.ap-northeast-2.compute.amazonaws.com/order";
        BitMatrix matrix = new MultiFormatWriter().encode(amazonUrl, BarcodeFormat.QR_CODE, width, height);

        MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, -1);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(matrix, "PNG", out, config);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return ResponseEntity.ok().headers(headers).body(out.toByteArray());
        }
    }
}

/*
@RestController
@RequestMapping("/qr")
public class TableRegisterController {
    @GetMapping("qr")
    public Object createQr(@RequestParam String url) throws WriterException, IOException {
        int width = 500;
        int height = 500;
        BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);

        System.out.println("url = " + url);


        MatrixToImageConfig conf = new MatrixToImageConfig(MatrixToImageConfig.BLACK,-1);


        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            MatrixToImageWriter.writeToStream(matrix, "PNG", out, conf);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray());
        }
    }

}*/
