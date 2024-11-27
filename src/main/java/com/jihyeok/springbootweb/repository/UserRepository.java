package com.jihyeok.springbootweb.repository;

import com.jihyeok.springbootweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 이메일을 통해 사용자 정보를 조회할 수 있도록.
}
