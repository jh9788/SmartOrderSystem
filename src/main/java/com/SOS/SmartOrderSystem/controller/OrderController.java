package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
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


    @PostMapping("/order")
    public Order postEndpoint(@RequestBody HashMap<String, Object> map) {

        String name = (String)map.get("name");
        int price = (int)map.get("price");


        log.info("map={}", map);

        Optional<Menu> foundMenu = menuRepository.findByName(name);
        Order order = new Order(++sequence, 1, foundMenu.get().getId(),1, 1);

        orderService.join(order);


        log.info("order.getId()={}", order.getId());
        log.info("order.getStoreId()={}", order.getStoreId());
        log.info("order.getMenuId()={}", order.getMenuId());
        log.info("order.getTableId()={}", order.getTableId());
        log.info("order.getQuantity()={}", order.getQuantity());

        return order;
    }
}
