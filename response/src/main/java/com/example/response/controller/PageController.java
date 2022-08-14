package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller라는 Annotation은
// String return시 리소스를 찾음
// ResponseBody라는 Annotation을 붙이면 ResponseBody를 만들어서 내리겠다는 의미
@Controller
public class PageController {

    // Page return 하는 서버

    // return이 String이면 자동으로 html 파일을 찾아감
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // Page Controller에서는 Json 바디를 안내리는게 맞음
    // Json을 내려주는 방법
    // 1. ResponseEntity

    // 2. ResponseBody
    @ResponseBody   // 객체 자체를
    @GetMapping("/user")
    public User user(){
        var user = new User(); // var : 타입을 추론하는 단축 약어
        user.setName("steve");
        user.setAddress("패스트 캠퍼스");
        return user;
    }
}
