package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryOwnerRepository implements OwnerRepository{

    private static Map<String, Owner> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Owner save(Owner owner) {

        store.put(owner.getId(), owner);

        return owner;
    }

    @Override
    public Optional<Owner> findById(String id) {
        store.get(id);
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
