package com.SOS.SmartOrderSystem.repository;

import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String>{
     boolean existsById(String id);
     Optional<Owner> findById(String id);
}
