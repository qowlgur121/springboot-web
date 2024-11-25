package com.jihyeok.springbootweb.repository;

import com.jihyeok.springbootweb.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Article 엔티티에 대한 데이터 접근 계층(Repository) 인터페이스.
 * Spring Data JPA 를 사용하여 Article 데이터를 관리한다.
 */
public interface BlogRepository extends JpaRepository<Article, Long> {
}
