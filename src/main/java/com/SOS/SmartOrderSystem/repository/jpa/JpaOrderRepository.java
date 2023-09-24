package com.SOS.SmartOrderSystem.repository.jpa;

import com.SOS.SmartOrderSystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
