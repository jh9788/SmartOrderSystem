package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);

    List<Order> findAll();
}
