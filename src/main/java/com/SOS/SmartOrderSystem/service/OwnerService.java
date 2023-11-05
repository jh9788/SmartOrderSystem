package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;

public interface OwnerService {
    public void join(JoinRequest joinRequest);
    Owner findOwnerID(String id);
    public Owner getLoginOwnerById(String loginId);
    public Owner login(LoginRequest loginRequest);
    public boolean checkLoginIdDuplicate(String loginId);
    Owner findOwnerPW(String pw);
}
