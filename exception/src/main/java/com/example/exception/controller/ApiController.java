package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {

    @GetMapping ("")// required = false : 값이 없더라도 동작시킴
    public User get(
            @Size(min = 2)
            @RequestParam(required = true) String name,

            @NotNull
            @Min(1)
            @RequestParam(required = true) Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);

//        int a = 10+age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    // Controller 안에 작성 시
    // 이 @ExceptionHandler 는 이 컨트롤러 안에서 일어나는 것만 관여
    // Global 에 지정된 @ExceptionHandler보다 컨트롤러 안에 지정된게 우선순위가 높음
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
//        System.out.println("api controller");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }

}
