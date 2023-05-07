package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Menu;

import java.util.List;
import java.util.Optional;

public class MemoryMenuRepository implements MenuRepository{

    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public Optional<Menu> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Menu> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }
}
