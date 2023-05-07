package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Order;

public interface OrderService {

    void join(Order order);

    Order findOrder(Long orderId);
}
