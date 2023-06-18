package com.SOS.SmartOrderSystem.controller;
import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@PostMapping("/api")
public class OwnerController {
    @PostMapping("/owner")
    public Owner owner(){
        Owner owner = new Owner();
        return owner;
    }

    public getById()
}
