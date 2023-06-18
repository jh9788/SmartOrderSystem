package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final MenuRepository menuRepository;
    static long sequence = 0L;
    @Autowired
    public OrderController(OrderService orderService, MenuRepository menuRepository) {
        this.orderService = orderService;
        this.menuRepository = menuRepository;
    }

    @ResponseBody
    @PostMapping("/order")
    public HashMap<String, Object> postEndpoint(@RequestBody HashMap<String, Object> map) {

        String name = (String)map.get("name");
        int price = (int)map.get("price");

        System.out.println("map = " + map);

        Optional<Menu> foundMenu = menuRepository.findByName(name);
        Order order = new Order(++sequence, 1, foundMenu.get().getId(),1, 1);

        orderService.join(order);


        System.out.println("order.getId() = " + order.getId());
        System.out.println("order.getStoreId() = " + order.getStoreId());
        System.out.println("order.getMenuId() = " + order.getMenuId());
        System.out.println("order.getTableId() = " + order.getTableId());
        System.out.println("order.getQuantity() = " + order.getQuantity());

        return map;
    }
}
