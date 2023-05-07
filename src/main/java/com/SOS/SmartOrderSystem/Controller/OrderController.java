package com.SOS.SmartOrderSystem.Controller;

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

        System.out.println("map = " + map);

        return map;
    }
}
