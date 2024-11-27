package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // Lombok 어노테이션: 생성자 자동 생성
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스 구현한 클래스
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) { // 사용자 이메일을 이용해 사용자 정보 조회

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
