package com.SOS.SmartOrderSystem.repository.jpa;

import com.SOS.SmartOrderSystem.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface JpaOwnerRepository extends JpaRepository<Owner, String> {
}
