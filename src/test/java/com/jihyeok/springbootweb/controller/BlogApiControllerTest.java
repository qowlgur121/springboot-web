package com.jihyeok.springbootweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jihyeok.springbootweb.domain.Article;
import com.jihyeok.springbootweb.dto.AddArticleRequest;
import com.jihyeok.springbootweb.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // Spring Boot 환경을 구동하여 테스트를 진행한다.
@AutoConfigureMockMvc // MockMvc를 자동으로 설정한다. MockMvc는 실제 웹 서버를 사용하지 않고 HTTP 요청을 모의(mock)하여 테스트를 수행하는 도구.
class BlogApiControllerTest {

    /**
     * MockMvc 객체는 HTTP 요청을 모의(mock)하여 테스트를 수행하는 데 사용된다.
     * 실제 웹 서버 없이 테스트를 진행할 수 있도록 도와준다.
     */
    @Autowired // 자동으로 객체를 주입
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * WebApplicationContext는 Spring 웹 애플리케이션의 컨텍스트 정보를 담고 있습니다.
     * MockMvc 설정에 사용됩니다.
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * HTTP에서는 데이터를 JSON이나 XML 같은 텍스트 기반 형식으로 주고받는데 자바는 객체를 사용하기 때문에 서로 변환할 수 있어야 한다.
     *
     * 직렬화: 자바 객체 -> JSON
     * 역직렬화: JSON -> 자바 객체
     *
     * 이런 변환을 해주는게 ObjectMapper
     */
    @Autowired
    BlogRepository blogRepository;

    /**
     * 각 테스트 메서드가 실행되기 전에 한 번씩 실행되는 메서드이다.
     * MockMvc를 설정하고, 테스트를 위해 BlogRepository의 모든 데이터를 삭제한다.
     */
    @BeforeEach // 각 테스트 메서드 실행 전에 실행
    public void mockMvcSetUp() {
        /**
         * MockMvc를 설정합니다. WebApplicationContext를 사용하여 실제 스프링 애플리케이션의 환경을 반영한다.
         */
        MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll(); // 테스트 시작 전에 데이터베이스의 모든 데이터를 삭제하여 테스트 간의 독립성을 보장한다.
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {

        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체를 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url) //// MockMvc를 사용하여 POST 요청
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = blogRepository.findAll();

        Assertions.assertThat(articles.size()).isEqualTo(1);
        Assertions.assertThat(articles.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
}