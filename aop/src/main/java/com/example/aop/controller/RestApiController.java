package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Timer
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {

//        System.out.println("get method");
//        System.out.println("get method : " + id);
//        System.out.println("get method : " + name);

        return id + " " + name;

    }

    @Timer
    @PostMapping("/post")
    public User post(@RequestBody User user) {

//        System.out.println("post method : " + user);

        return user;

    }

    // method 걸리는 시간 재기
    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        // AOP에서 안짜고 METHOD에서 짜면 각 METHOD마다 같은 코드 반복 필요

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // db logic
        Thread.sleep(1000*2);

        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());

    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user) {

        System.out.println("put");
        System.out.println(user);
        return user;

    }

}
