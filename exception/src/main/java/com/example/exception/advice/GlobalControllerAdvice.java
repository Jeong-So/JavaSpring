package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice  // View 사용 시 시용하는 advice
// 패키지 지정 시 해당 패키지 하위 예외 다 잡음
@RestControllerAdvice(basePackages = "com.example.exception.controller") // Rest Controller 사용시 시용하는 advice
public class GlobalControllerAdvice {

    // RestApii 는 ResponseEntity를 뱉음
    @ExceptionHandler(value = Exception.class) // 모든 예외를 다 잡음
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println("===================");
        System.out.println(e.getLocalizedMessage());
        System.out.println("===================");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 특정 Method의 예외를 잡을 시
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
