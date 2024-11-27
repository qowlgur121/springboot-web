package com.jihyeok.springbootweb.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users") // 데이터베이스 테이블 이름을 users로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 직접 생성하지 못하도록 기본 생성자 제한
@Getter
@Entity
public class User implements UserDetails { // Spring Security의 UserDetails 인터페이스 구현
    @Id // 기본키 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 및 증가되는 기본키
    @Column(name = "id", updatable = false) // 데이터베이스 컬럼 이름, 업데이트 불가능
    private Long id;

    @Column(name = "email", nullable = false, unique = true) // 이메일, NULL 허용 안 함, 유니크 제약조건
    private String email;

    @Column(name = "password")
    private String password;

    @Builder // Lombok 어노테이션: 빌더 패턴을 사용하여 객체 생성
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 사용자 권한 정보 반환
        return List.of(new SimpleGrantedAuthority("user")); // "user" 권한을 가진 사용자로 설정
    }

    @Override
    public String getUsername() { // 사용자 이름 (로그인 ID) 반환
        return email; // 이메일을 사용자 이름으로 사용
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부 (true: 만료되지 않음)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠김 여부 (true: 잠기지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 만료 여부 (true: 만료되지 않음)
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정 활성화 여부 (true: 활성화됨)
        return true;
    }
}