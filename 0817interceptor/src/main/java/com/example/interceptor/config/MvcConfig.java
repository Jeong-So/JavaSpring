package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@RequiredArgsConstructor  // final로 선언된 개체들을 생성자에 주입해줌 (@Authwired대신 생성자에서 주입)
public class MvcConfig implements WebMvcConfigurer {

    // @Autowired로 자기 자신을 넣을 수 있지만 순환참조가 생길 수 있음
//    @Autowired
//    private final AuthInterceptor authInterceptor;

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
        // addPathPatterns : 검사하고 싶은 패턴 넣음 /api/private/ 하위 모든것 만 검사하겠다
        // excludePathPatterns : 검사 제외하고 싶은 패턴

    }
}
