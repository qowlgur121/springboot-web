package com.jihyeok.springbootweb.service;

import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.AddArticleRequest;
import com.jihyeok.springbootweb.dto.UpdateArticleRequest;
import com.jihyeok.springbootweb.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //해당 ID의 게시글을 데이터베이스에서 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 해당 메서드 내에서 발생하는 모든 데이터베이스 작업은 하나의 트랜잭션으로 관리
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id) // findById() 메서드는 Optional<Article> 객체를 반환. Optional은 값이 있을 수도 있고 없을 수도 있음을 나타내는 객체
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id)); //Optional 객체에 값이 없을 경우(해당 ID의 게시글이 없을 경우), IllegalArgumentException 예외를 발생시킨다.

        article.update(request.getTitle(), request.getContent()); // 값 업데이트

        return article; // 업데이트된 Article 객체를 반환
    }
}
