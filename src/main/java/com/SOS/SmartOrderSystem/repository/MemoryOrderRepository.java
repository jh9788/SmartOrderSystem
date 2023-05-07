package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryOrderRepository implements OrderRepository {
    private static Map<Long, Order> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Order save(Order order) {
        order.setId(++sequence);
        store.put(order.getId(), order);

        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        store.get(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){store.clear();}

}
