package com.jihyeok.springbootweb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {

    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam("code") int code) { // 요청 URL의 쿼리 파라미터 code를 int형 변수 code에 저장한다.
        switch (code) {
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code) { // 요청 본문의 JSON 데이터를 Code 객체로 변환하여 code 변수에 저장한다.
        switch (code.value()) {
            case 1:
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                return ResponseEntity.ok().body("OK!");
        }
    }

    // 간단한 레코드 클래스: 요청 본문에서 JSON 데이터를 받기 위한 클래스
    record Code(int value) {}
}
