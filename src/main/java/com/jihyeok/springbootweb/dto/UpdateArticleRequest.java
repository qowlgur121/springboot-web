package com.jihyeok.springbootweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Lombok 어노테이션: 기본 생성자를 자동으로 생성한다.
@AllArgsConstructor // Lombok 어노테이션: 모든 필드를 포함하는 생성자를 자동으로 생성한다.
@Getter // Lombok 어노테이션: 모든 필드에 대한 getter 메서드를 자동으로 생성한다.
public class UpdateArticleRequest {
    private String title;
    private String content;
}
