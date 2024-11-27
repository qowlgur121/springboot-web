package com.jihyeok.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // @EnableJpaAuditing 어노테이션을 사용하면, @CreatedDate와 @LastModifiedDate 어노테이션이 붙은 필드에 엔티티의 생성 및 수정 시간이 자동으로 기록된다.
@SpringBootApplication
public class SpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}

