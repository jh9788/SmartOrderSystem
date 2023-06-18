package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Owner;

public interface OwnerService {
    void join(Owner owner);

    Owner findOwnerID(String id);

    Owner findOwnerPW(String pw);
}
