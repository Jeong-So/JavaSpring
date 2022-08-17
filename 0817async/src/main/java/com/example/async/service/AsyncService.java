package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Async
    public CompletableFuture run(){
        return new AsyncResult(hello()).completable();
    }
    // CompletableFuture가 반환형일때 별도의 Thread에서 실행 시킴

    @Async // 비동기로 동작가능하게 함
    public String hello(){

        /*
        Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {

            }
        }))
         */
        // 이렇게도 작성 가능하나 스프링에서는
        // 어플리케이션에 @EnableAsync 넣고 비동기로 하고 싶은 곳에 @Async 붙이면 비동기로 동작


        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep ...");
//                System.out.println("thread sleep ...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return "async hello";
    }
}
