package com.SOS.SmartOrderSystem.controller;
import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import com.SOS.SmartOrderSystem.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @PostMapping("/Join")
    public HashMap<String, Object> postEndpoint(@RequestBody HashMap<String, Object> map) {

        String name = (String)map.get("name");
        String username = (String)map.get("username");
        String password = (String)map.get("password");
        String email = (String)map.get("email");
        String phone = (String)map.get("phone");
        String gender = (String)map.get("gender");

        System.out.println("map = " + map);

        //Optional<Owner> foundOwner = ownerRepository.findById(name);
        Owner owner = new Owner(username, password, name, gender,email, phone);
        ownerService.join(owner);

        System.out.println("owner.getId() = " + owner.getId());
        System.out.println("owner.getEmail() = " + owner.getName());
        System.out.println("owner.getPassword() = " + owner.getPassword());
        System.out.println("owner.getEmail() = " + owner.getEmail());
        System.out.println("owner.getPhoneNumber() = " + owner.getPhoneNumber());
        System.out.println("owner.getSex() = " + owner.getSex());

        return map;
    }

}
