package com.SOS.SmartOrderSystem.service;

import com.SOS.SmartOrderSystem.auth.JwtTokenProvider;
import com.SOS.SmartOrderSystem.domain.Owner;
import com.SOS.SmartOrderSystem.domain.dto.JoinRequest;
import com.SOS.SmartOrderSystem.domain.dto.LoginRequest;
import com.SOS.SmartOrderSystem.repository.OwnerRepository;
import com.SOS.SmartOrderSystem.repository.jpa.JpaOwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final JpaOwnerRepository ownerRepository; // final 필수
    private final JwtTokenProvider jwtTokenProvider;

    // Spring Security를 사용한 로그인 구현 시 사용
    //private final BCryptPasswordEncoder encoder;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * loginId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */

    public boolean checkLoginIdDuplicate(String loginId) {
        if(ownerRepository.findById(loginId).isEmpty()){
            return false;
        }
        else
            return true;
        //return ownerRepository.existsById(loginId);
    }

    /**
     * 회원가입
     * 화면에서 JoinRequest(loginId, password)을 입력받아 Owner로 변환 후 저장
     * loginId 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     * 비밀 번호 암호화해서 저장
     */
    public void join(JoinRequest joinRequest)
    {
        Owner ownerEntity = new Owner(joinRequest);

        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(ownerEntity.getPassword());
        ownerEntity.setPassword(encodedPassword);

        ownerRepository.save(ownerEntity); // db에 저장
    };

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 Owner return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public Owner login(LoginRequest loginRequest) {
        Optional<Owner> optionalOwnerEntity = ownerRepository.findById(loginRequest.getId()); // db에서 회원가입된 id를 찾음

        // loginId와 일치하는 Owner가 없으면 null return
        if(optionalOwnerEntity.isEmpty()) {
            System.out.println("loginId와 일치하는 Owner가 없으면 null return");
            return null;
        }

        Owner ownerEntity = optionalOwnerEntity.get();

        // 찾아온 Owner의 password와 입력된 password가 다르면 null return
        if(!passwordEncoder.matches(loginRequest.getPassword(),ownerEntity.getPassword())){
            System.out.println("로그인 실패");
            return null;
        }
        System.out.println("로그인 성공");
        return ownerEntity; // 로그인 성공, 객체 반환
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
