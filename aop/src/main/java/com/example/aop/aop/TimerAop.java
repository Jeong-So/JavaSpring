package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect     // aop로 동작하기 위한 annotation
@Component    // Bean과 Component 의 차이 : Bean같은 경우는 Class에 붙일 수 없음, Bean은 method에 붙임
              // Component와 Configration 의 차이 : Configration은 하나의 class에 여러개의 Bean 등록 가능
public class TimerAop {

    // execution : 어디에 적용 (rule)
    // com.example.aop라는 프로젝트의 controller라는 패키지의 하위의 모든 메소드를 다 aop로 보겠다
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){}

    // 프로세스/method가 실행되는 시간 측정 (aop)
    @Around("cut() && enableTimer()")  // cut()과 enableTimer() 조건 같이 쓰겠다
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        // 실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed(); // method 실행

        // 실행 후
        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());

    }

}
