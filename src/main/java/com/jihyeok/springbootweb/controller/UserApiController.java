package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.dto.AddUserRequest;
import com.jihyeok.springbootweb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        return "redirect:/login"; // 사용자 등록 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, // 현재 사용자의 세션을 무효화
                SecurityContextHolder.getContext().getAuthentication()); // 현재 인증된 사용자의 인증 정보를 가져옴
        return "redirect:/login"; // 로그아웃이 성공적으로 완료되면 /login URL로 리다이렉트
    }
}