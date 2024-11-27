package com.jihyeok.springbootweb.controller;

import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.ArticleListViewResponse;
import com.jihyeok.springbootweb.dto.ArticleViewResponse;
import com.jihyeok.springbootweb.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream() // List<Article>을 Stream으로 변환하고
                .map(ArticleListViewResponse::new) // ArticleListViewResponse 클래스의 생성자를 호출하여 Article 객체를 ArticleListViewResponse 객체로 매핑해서 Stream의 각 Article 객체를 ArticleListViewResponse 객체로 변환하고
                .toList(); // Stream 연산 결과를 List<ArticleListViewResponse>로 변환한다.

        model.addAttribute("articles", articles); // 블로그 글 리스트 저장. 구체적으로는 뷰에다가 데이터를 전달해줄 모델 객체에다가 값 넣기

        return "articleList"; // 뷰 이름 반환. 스프링은 articleList.html라는 뷰 조회한다.
    }


    /**
     * /articles/{id}로 GET 요청이 들어오면, getArticle() 메서드가 실행된다.
     * 메서드는 요청 URL에서 ID를 추출하고,
     * BlogService를 사용하여 해당 ID의 게시글을 데이터베이스에서 조회한다.
     * 조회된 게시글은 ArticleViewResponse DTO로 변환되어 model에 추가되고,
     * "article"이라는 이름의 Thymeleaf 템플릿이 렌더링한다.
     */
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }


    //새로운 게시글을 작성하거나 기존 게시글을 수정하는 기능을 위한 컨트롤러 메서드
    /**
     * 요청에 따라 새로운 ArticleViewResponse 객체를 생성하거나,
     * 기존 게시글 정보를 담은 ArticleViewResponse 객체를 생성하여 Thymeleaf 템플릿에 전달
     * id가 null인 경우(새로운 게시글 작성)
     * id가 null이 아닌 경우(기존 게시글 수정)
     */
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
