package com.jihyeok.springbootweb.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;


/**
 * 이 클래스는 /thymeleaf/example 경로로 GET 요청이 들어왔을 때,
 * Thymeleaf 템플릿 엔진을 사용하여 템플릿을 렌더링하는 역할을 한다.
 */
@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) { //Model 객체는 HTML 같은 뷰에 데이터를 넘겨주는 객체이다.
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서")); //List.of() 메서드를 사용해서 불변 리스트를 생성

        // 뷰에 데이터를 넘겨줄 모델 객체에 값 넣기.
        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        /**
         * @Controller 어노테이션이 붙은 클래스 내의 메서드가 문자열을 반환하면,
         * 스프링은 이 문자열을 사용하여 적절한 뷰를 찾아 렌더링한다.
         * 예를 들어 example.html 파일이 src/main/resources/templates 디렉토리에 존재해야 함.
         */
        return "example"; // example이라는 이름의 Thymeleaf 템플릿을 렌더링하도록 지시.
    }

    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
