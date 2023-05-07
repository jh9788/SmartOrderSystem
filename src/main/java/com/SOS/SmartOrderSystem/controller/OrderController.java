package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api")
public class OrderController {

    @ResponseBody
    @PostMapping("/order")
    public HashMap<String, Object> postEndpoint(@RequestBody HashMap<String, Object> map) {


       /* Menu menu = new Menu(requestDto.getName(), requestDto.getPrice());
        System.out.println("menu = " + menu.getName());
        System.out.println("price = " + menu.getPrice());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("menu_rq", requestDto);
        return map;*/
        String name = (String)map.get("name");
        int price = (int)map.get("price");

        Menu menu = new Menu(name, price);

        System.out.println("menu.getName() = " + menu.getName());
        System.out.println("menu.getPrice() = " + menu.getPrice());
        System.out.println("map = " + map);

        return map;
    }
}
