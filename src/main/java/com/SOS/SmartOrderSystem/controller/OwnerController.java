package com.SOS.SmartOrderSystem.controller;
import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.OwnerParam;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import com.SOS.SmartOrderSystem.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerRepository ownerRepository;

    static long sequence = 0L;

    @Autowired
    public OwnerController(OwnerService ownerService, OwnerRepository ownerRepository) {
        this.ownerService = ownerService;
        this.ownerRepository = ownerRepository;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody Owner owner) {

        log.info("owner joined={}", owner);

        boolean join = ownerService.join(owner);

        if(join == true){
            return new ResponseEntity<String>("Join successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Join failed", HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody OwnerParam ownerParam) {



        log.info("owner logined={}", ownerParam);


        //Optional<Owner> foundOwner = ownerRepository.findById(id);

        boolean accountValid = ownerRepository.isAccountValid(ownerParam.getId(), ownerParam.getPassword());

        // HttpResponse 객체 생성
        //HttpResponse<String> response = ("OK", "200")

        // ResponseEntity를 사용하여 HttpResponse를 포함하는 응답 생성
       // return ResponseEntity.status(HttpStatus.OK).body(response);

        if(accountValid ==true){

            return new ResponseEntity<String>("Login successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
        }

    }

}
