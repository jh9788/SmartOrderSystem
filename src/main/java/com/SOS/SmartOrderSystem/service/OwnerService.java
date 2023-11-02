package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Menu;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface OwnerService {
    public void join(JoinRequest joinRequest);

    public void join2(JoinRequest joinRequest);

    Owner findOwnerID(String id);
    public Owner getLoginOwnerById(String loginId);
    public Owner login(LoginRequest loginRequest);
    public boolean checkLoginIdDuplicate(String loginId);
    Owner findOwnerPW(String pw);
}
