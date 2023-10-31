package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner save(Owner owner);
    Optional<Owner> findById(String id);

    boolean isAccountValid(String id, String password);
    List<Owner> findAll();
}
