package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    Owner save(Owner owner);
    Optional<Owner> findById(String id);

    List<Owner> findAll();
}
