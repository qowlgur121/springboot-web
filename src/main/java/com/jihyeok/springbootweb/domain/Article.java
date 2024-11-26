package com.jihyeok.springbootweb.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Article 엔티티 클래스. 게시글을 나타낸다.
 */
@Entity // JPA 엔티티 클래스임을 나타내는 어노테이션.  데이터베이스 테이블과 매핑된다.
@Getter // Lombok 어노테이션: 모든 필드에 대한 getter 메서드를 자동으로 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok 어노테이션:외부에서 직접 생성할 수 없도록 접근 제어자를 protected 로 설정한 기본 생성자를 생성한다.
public class Article {

    /**
     * 기본키 (ID).  자동 생성 및 증가되는 Long 타입.
     */
    @Id // id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // // 기본키 생성 전략: 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false) //// 데이터베이스 컬럼 이름은 'id', 업데이트 불가능.
    private Long id;

    /**
     * 게시글 제목.  NOT NULL 제약조건이 있다.
     */
    @Column(name = "title", nullable = false) //데이터베이스 컬럼 이름은 'title', NULL 값 허용 안 함.
    private String title;

    /**
     * 게시글 내용.  NOT NULL 제약조건이 있다.
     */
    @Column(name = "content", nullable = false) // 데이터베이스 컬럼 이름은 'content', NULL 값 허용 안 함.
    private String content;

    @Builder // Lombok의 @Builder 어노테이션.  빌더 패턴을 사용하기 위한 코드를 자동으로 생성하므로 객체 생성을 간소화한다.
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
