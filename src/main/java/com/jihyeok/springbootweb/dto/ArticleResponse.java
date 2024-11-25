package com.jihyeok.springbootweb.dto;

import com.jihyeok.springbootweb.domain.Article;
import lombok.Getter;

/**
 * 게시글 정보를 반환하는 응답 정보를 담는 DTO(Data Transfer Object) 클래스이다.
 * 게시글의 제목(title)과 내용(content)을 담고 있다.
 * Controller 에서 사용된다.
 */
@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
