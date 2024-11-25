package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.AddArticleRequest;
import com.jihyeok.springbootweb.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API 방식으로 블로그 게시글을 관리하는 Controller
 */
@RequiredArgsConstructor // Lombok 어노테이션: final 필드를 가지는 생성자를 자동으로 생성한다.
@RestController // Spring: REST API 컨트롤러임을 나타냄. HTTP 응답으로 객체 데이터를 JSON형태로 반환한다.
public class BlogApiController {

    private final BlogService blogService; // BlogService 의존성 주입

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request); // BlogService의 save() 메서드를 호출하여 새로운 게시글을 저장하고, 저장된 Article 객체를 savedArticle 변수에 저장

        // BlogService를 통해 게시글 저장이 성공하면, 저장된 게시글 정보를 담은 Article 객체를 HTTP 상태 코드 201과 함께 클라이언트에게 JSON 또는 XML 형식으로 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);

    }
}
