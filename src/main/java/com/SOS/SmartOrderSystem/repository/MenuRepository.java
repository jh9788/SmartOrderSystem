package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Menu save(Menu menu);
    Optional<Menu> findById(Long id);
    Optional<Menu> findByName(String name);

    List<Menu> findAll();

}
