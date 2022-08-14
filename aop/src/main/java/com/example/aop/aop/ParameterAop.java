package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect  // aop로 동작하기 위한 annotation
@Component  // spring에서 관리
public class ParameterAop {

    // execution : 어디에 적용 (rule)
    // com.example.aop라는 프로젝트의 controller라는 패키지의 하위의 모든 메소드를 다 aop로 보겠다
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    // Pointcut을 통해 외부에서 Method를 직접 print할 수 있기 때문에
    // 직업 RestApiController에서 print해 줄 필요 없음
    @Before("cut()")  // 언제 메소드 실행 시킬지 : cut() 메소드 실행 전에 실행
    public void before(JoinPoint joinPoint){  // JointPoint : 들어가는 지점에 대한 정보를 가지고 있는 객체

        // method 이름 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 메소드 가져올 수 있음
        Method method = methodSignature.getMethod();
        System.out.println(method.getName()); // 메소드 이름 가져오기

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")  // 반환값을 어디서 할거냐 : Pointcut을 만들어놓은 cut()에서  // 내가 받고싶은 객체 이름 넣어줌
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {  // returning = 값, Object 객체 이름과 같이 해야함
        System.out.println("return obj");
        System.out.println(returnObj);
    }


}
