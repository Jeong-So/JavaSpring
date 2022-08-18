package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    // 옛날방식
//    @Autowired
//    private RestTemplateService restTemplateService;

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public UserResponse getUser(){ // String -> UserResponse
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse getPost(){ // String(1) -> UserResponse(2)
        restTemplateService.post();
        return new UserResponse();
//        return restTemplateService.post(); // 1
    }

    @GetMapping("/exchange")
    public UserResponse getExchange(){ // String(1) -> UserResponse(2)
        restTemplateService.exchange();
        return new UserResponse();
//        return restTemplateService.post(); // 1
    }


    @GetMapping("/generic-exchange")
    public Req<UserResponse> getGenericExchange(){ //
        return restTemplateService.genericExchange();
    }

//    @PostMapping("/user")
//    public UserResponse getUser(){ // String -> UserResponse
//        return restTemplateService.post();
//    }
}
