package com.jihyeok.springbootweb.service;

import com.jihyeok.springbootweb.domain.User;
import com.jihyeok.springbootweb.dto.AddUserRequest;
import com.jihyeok.springbootweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 사용자 계정 생성 및 관리 서비스
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // 비밀번호 암호화 객체 주입

    /**
     * 새로운 사용자 계정을 생성하고 데이터베이스에 저장합니다.
     * @param dto 사용자 정보를 담은 AddUserRequest 객체
     * @return 저장된 사용자의 ID
     */
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId(); // 저장된 사용자 객체의 ID를 반환
    }
}