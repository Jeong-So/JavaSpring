package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    // execution : 어디에 적용 (rule)
    // com.example.aop라는 프로젝트의 controller라는 패키지의 하위의 모든 메소드를 다 aop로 보겠다
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {

        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            if(arg instanceof User){
                User user = User.class.cast(arg); // User라는 클래스로 형변환
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                user.setEmail(email);
            }
        }

    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String Base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(Base64Email);

        }
    }
}
