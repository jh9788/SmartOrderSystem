package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl {

    private final OwnerRepository ownerRepository;

    // Spring Security를 사용한 로그인 구현 시 사용
    private final BCryptPasswordEncoder encoder;

    /**
     * loginId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginIdDuplicate(String loginId) {
        return ownerRepository.existsById(loginId);
    }

    /**
     * 회원가입 기능 1
     * 화면에서 JoinRequest(loginId, password)을 입력받아 Owner로 변환 후 저장
     * loginId 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join(JoinRequest joinRequest) {
        ownerRepository.save(joinRequest.toEntity())
    };

    /**
     * 회원가입 기능 2
     * 화면에서 JoinRequest(loginId, password)을 입력받아 Owner로 변환 후 저장
     * 회원가입 1과는 달리 비밀번호를 암호화해서 저장
     * loginId 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void join2(JoinRequest joinRequest) {
        ownerRepository.save(joinRequest.toEntity(encoder.encode(joinRequest.getPassword())));
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 Owner return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public Owner login(LoginRequest loginRequest) {
        Optional<Owner> optionalOwner = ownerRepository.findById(loginRequest.getId());

        // loginId와 일치하는 Owner가 없으면 null return
        if(optionalOwner.isEmpty()) {
            return null;
        }

        Owner Owner = optionalOwner.get();

        // 찾아온 Owner의 password와 입력된 password가 다르면 null return
        if(!Owner.getPassword().equals(loginRequest.getPassword())) {
            return null;
        }

        return Owner;
    }

    /**
     * OwnerId(Long)를 입력받아 Owner을 return 해주는 기능
     * 인증, 인가 시 사용
     * OwnerId가 null이거나(로그인 X) OwnerId로 찾아온 Owner가 없으면 null return
     * OwnerId로 찾아온 Owner가 존재하면 Owner return
     */
    public Owner getLoginOwnerById(String loginId) {
        if(loginId == null) return null;

        Optional<Owner> optionalOwner = ownerRepository.findById(loginId);
        if(optionalOwner.isEmpty()) return null;

        return optionalOwner.get();
    }
}
