package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Order;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    void join(Menu menu);

    Order findOrder(Long menuId);
}
