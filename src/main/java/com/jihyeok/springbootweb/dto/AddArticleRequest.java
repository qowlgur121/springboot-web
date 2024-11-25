package com.jihyeok.springbootweb.dto;

import com.jihyeok.springbootweb.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 새로운 게시글을 추가하기 위한 요청 정보를 담는 DTO(Data Transfer Object) 클래스이다.
 * Controller 에서 사용된다.
 */
@NoArgsConstructor // Lombok 어노테이션: 기본 생성자를 자동으로 생성한다.
@AllArgsConstructor // Lombok 어노테이션: 모든 필드를 포함하는 생성자를 자동으로 생성한다.
@Getter // Lombok 어노테이션: 모든 필드에 대한 getter 메서드를 자동으로 생성한다.
public class AddArticleRequest {

    private String title; // 게시글 제목

    private String content; // 게시글 내용

    /**
     * 이 메서드는 추후에 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도.
     * AddArticleRequest 객체를 Article 엔티티 객체로 변환한다.
     * 그니까 Controller에서 받은 요청 데이터를 데이터베이스에 저장할 수 있는 Article 객체로 변환하는 역할을 한다.
     */
    public Article toEntity() { // toEntity()는 빌터 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드.
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
