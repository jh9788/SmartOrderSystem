package com.SOS.SmartOrderSystem.repository.jpa;

import com.SOS.SmartOrderSystem.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuRepository extends JpaRepository<Menu, Long> {
}
