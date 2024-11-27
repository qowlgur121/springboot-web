package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.dto.AddUserRequest;
import com.jihyeok.springbootweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
}