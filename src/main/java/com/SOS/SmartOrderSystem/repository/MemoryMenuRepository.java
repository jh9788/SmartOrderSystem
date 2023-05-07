package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMenuRepository implements MenuRepository{

    private static Map<Long, Menu> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Menu save(Menu menu) {
        menu.setId(++sequence);
        store.put(menu.getId(), menu);

        return menu;
    }

    @Override
    public Optional<Menu> findById(Long id) {
        store.get(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Menu> findByName(String name) {
        return store.values().stream()
                .filter(menu -> menu.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
