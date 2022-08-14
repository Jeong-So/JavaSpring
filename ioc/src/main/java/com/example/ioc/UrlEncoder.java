package com.example.ioc;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

// Spring이 실행이될 때 @Component 붙은 클래스 찾아서 직접 객체를 Singleton 형태로 만들어서 Spring 컨테이너에서 관리
// @Component 이름 만들기 @Component("이룸")
@Component("url_encoder")
public class UrlEncoder implements IEncoder{
    public String encode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
