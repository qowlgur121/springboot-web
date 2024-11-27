package com.jihyeok.springbootweb.config;

import com.jihyeok.springbootweb.controller.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration // Spring 설정 클래스임을 나타내는 어노테이션
@EnableWebSecurity // Spring Security 설정 활성화
@RequiredArgsConstructor // 생성자 자동 생성
public class WebSecurityConfig {


    private final UserDetailService userService;


    // 여기에 해당하는 곳은 스프링 시큐리티 기능을 비활성화한다.
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring() // 특정 경로에 대한 Spring Security 무시 설정
                .requestMatchers(toH2Console()) // H2 콘솔 경로 무시
                .requestMatchers(new AntPathRequestMatcher("/static/**")); // 정적 리소스 경로 무시
    }


    // 특정 HTTP 요청에 대해 웹 기반 보안을 구성한다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth // 요청 권한 설정
                        .requestMatchers( // 다음 경로는 인증 없이 허용
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll() // 인증 없이 허용
                        .anyRequest().authenticated()) // 다른 모든 요청은 인증 필요
                .formLogin(formLogin -> formLogin // 폼 로그인 설정
                        .loginPage("/login") // 로그인 페이지 URL
                        .defaultSuccessUrl("/articles") // 로그인 성공 후 이동할 URL
                )
                .logout(logout -> logout // 로그아웃 설정
                        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                )
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (보안상 위험하니까 테스트용으로만 사용하자)
                .build(); // SecurityFilterChain 객체 생성
    }

    // 인증 관리자 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}