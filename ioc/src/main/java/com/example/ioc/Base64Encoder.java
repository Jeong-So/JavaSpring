package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

// @Component 이 클래스는 Spring이 Bean으로 만들어서 Spring이 직접 관리해 달라는 표시
@Component("base64Encoder")
public class Base64Encoder implements IEncoder {
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
