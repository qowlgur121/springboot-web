package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.AddArticleRequest;
import com.jihyeok.springbootweb.dto.ArticleResponse;
import com.jihyeok.springbootweb.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream() // List<Article>을 Stream으로 변환하고
                .map(ArticleResponse::new) // ArticleResponse 클래스의 생성자를 호출하여 Article 객체를 ArticleResponse 객체로 매핑해서 Stream의 각 Article 객체를 ArticleResponse 객체로 변환하고
                .toList(); // Stream 연산 결과를 다시 List<ArticleResponse>로 변환한다.

        // Spring의 ResponseEntity를 사용하여 HTTP 응답을 생성
        return ResponseEntity.ok() // ok()는 HTTP 상태 코드 200 (OK)설정하고
                .body(articles); // 데이터베이스에서 가져온 게시글 목록을 클라이언트에게 JSON 형태로 반환한다. @RestController는 객체를 JSON, XML 등으로 변환하여 응답으로 보냄.
    }

    /**
     * {id}는 경로 변수(path variable)를 나타내며, 삭제할 게시글의 ID를 나타낸다.
     * 예를 들어, 게시글 ID가 1인 게시글을 삭제하려면 /api/articles/1로 DELETE 요청을 보내야 한다.
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) { //<Void>는 응답 본문(body)에 데이터가 없음을 의미.
        // @PathVariable 어노테이션은 경로 변수 {id}의 값을 id라는 이름의 long 타입 변수에 할당
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
