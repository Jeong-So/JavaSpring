package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Async("async-thread")  // @Async는 aop기반이기 때문에 proxy패턴을 타서 public 메소드만 사용 가능
    public CompletableFuture run(){
//        hello(); // hello() 호출시 동작하지 않음, 같은 클래스내에서 같은 메소드를 호출할 때는 @Async를 타지 않음
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
