package com.jihyeok.springbootweb.service;

import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.AddArticleRequest;
import com.jihyeok.springbootweb.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 블로그 게시글 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@RequiredArgsConstructor // Lombok 어노테이션: final 필드를 가지는 생성자를 자동으로 생성한다.
@Service // Spring 어노테이션: Spring Bean으로 등록한다.
public class BlogService {

    private final BlogRepository blogRepository; // BlogRepository 의존성 주입

    /**
     * 새로운 게시글을 저장합니다.
     * AddArticleRequest DTO를 받아 Article 엔티티로 변환하고,  BlogRepository를 이용해 데이터베이스에 저장한다.
     * @param request 새로운 게시글 정보를 담은 AddArticleRequest 객체
     * @return 저장된 Article 엔티티 객체
     */
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity()); // request 객체를 엔티티로 변환 후 저장
    }
}
