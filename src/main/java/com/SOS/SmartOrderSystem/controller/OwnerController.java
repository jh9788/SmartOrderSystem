package com.SOS.SmartOrderSystem.controller;
import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import com.SOS.SmartOrderSystem.service.OwnerService;
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
    public ResponseEntity<String> join(@RequestBody HashMap<String, Object> map) {

        String name = (String)map.get("name");
        String id = (String)map.get("id");
        String password = (String)map.get("password");
        String email = (String)map.get("email");
        String phone = (String)map.get("phone");
        String gender = (String)map.get("gender");

        System.out.println("map = " + map);

        //Optional<Owner> foundOwner = ownerRepository.findById(name);
        Owner owner = new Owner(id, password, name, gender,email, phone);

        boolean join = ownerService.join(owner);

        if(join == true){
            return new ResponseEntity<String>("Join successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("Join failed", HttpStatus.UNAUTHORIZED);
        }



/*        System.out.println("owner.getId() = " + owner.getId());
        System.out.println("owner.getEmail() = " + owner.getName());
        System.out.println("owner.getPassword() = " + owner.getPassword());
        System.out.println("owner.getEmail() = " + owner.getEmail());
        System.out.println("owner.getPhoneNumber() = " + owner.getPhoneNumber());
        System.out.println("owner.getSex() = " + owner.getSex());*/


    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody HashMap<String, Object> map) {


        String id = (String)map.get("id");
        String password = (String)map.get("password");


        System.out.println("map = " + map);

        //Optional<Owner> foundOwner = ownerRepository.findById(id);

        boolean accountValid = ownerRepository.isAccountValid(id, password);

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
