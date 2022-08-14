package com.example.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 Class 는 REST API를 처리하는 Controller로 등록
@RequestMapping("/api") // RequestMapping URI를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello")   // 해당주소 : http://localhost:9090/api/hello  , 주소를 지정해주는 Annotation
    public String hello(){
        return "hello spring boot!";
    }
    /* 설명
    1. Spring에서 컨트롤러를 작동시키기 위해서는
     1) Class 를 만든 후 @RestController 라는 Annotation을 붙여줘야 함
     2) 주소를 할당하기 위해서는 @RequestMapping 을 사용해야 함

    2. GET 방식으로 API를 열어주기 위해서는 @GetMapping("주소") 들어가야함
     */

}
