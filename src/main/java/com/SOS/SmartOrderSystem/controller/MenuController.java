package com.SOS.SmartOrderSystem.controller;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.repository.MenuRepository;
import com.SOS.SmartOrderSystem.repository.jpa.JpaMenuRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {
    private final JpaMenuRepository menuRepository;

    @Autowired
    public MenuController(JpaMenuRepository menuRepository) {
        this.menuRepository = menuRepository;

    }

    @PostConstruct
    public void init(){
        Menu menu1 = new Menu(1, "곱창전골", 30000);
        Menu menu2 = new Menu(2, "후라이드치킨", 20000);
        Menu menu3 = new Menu(3, "떡볶이", 18000);
        Menu menu4 = new Menu(4, "김치찌개", 18000);
        Menu menu5 = new Menu(5, "오뎅탕", 13000);
        Menu menu6 = new Menu(6, "감자튀김", 10000);
        Menu menu7 = new Menu(7, "생맥주", 5000);
        Menu menu8 = new Menu(8, "소주", 5000);
        Menu menu9 = new Menu(9, "매화수", 6000);

        this.menuRepository.save(menu1);
        this.menuRepository.save(menu2);
        this.menuRepository.save(menu3);
        this.menuRepository.save(menu4);
        this.menuRepository.save(menu5);
        this.menuRepository.save(menu6);
        this.menuRepository.save(menu7);
        this.menuRepository.save(menu8);
        this.menuRepository.save(menu9);
    }

}
