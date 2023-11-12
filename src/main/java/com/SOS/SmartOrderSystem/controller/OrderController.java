package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.repository.jpa.JpaMenuRepository;
import com.SOS.SmartOrderSystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final JpaMenuRepository menuRepository;
    static long sequence = 0L;
    @Autowired
    public OrderController(OrderService orderService, JpaMenuRepository menuRepository) {
        this.orderService = orderService;
        this.menuRepository = menuRepository;
    }

    @GetMapping("/order/image")
    public ResponseEntity<Resource> returnImage(@RequestParam(value = "store") String store,
                                         @RequestParam(value = "menu") String menu) throws MalformedURLException {
        //String path = "src/main/frontend/public/img/" + menu + ".jpg"; //이미지가 저장된 위치
        String path = "http://jh9788.ipdisk.co.kr:8000/list/HDD1/project-image/" + menu + ".jpg";
        //Resource resource = new FileSystemResource(path);
        Resource resource = new UrlResource(path);

        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
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
