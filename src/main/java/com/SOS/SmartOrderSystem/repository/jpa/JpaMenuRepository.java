package com.SOS.SmartOrderSystem.repository.jpa;

import com.SOS.SmartOrderSystem.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);
}
