package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Slf4j
@Component // spring에 의해 관리 필요
public class AuthInterceptor implements HandlerInterceptor {

    // intercepter의 핵심은 return false;면 동작하지 않음
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            // 1. 전처리
            // filter단 : request와 response에 대해서 변경 가능

            ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);  // 이 클래스 사용 시 몇번이고 읽을 수 있음
            ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

            // 2. 메인
            // doFilter을 해야지 ContentCachingRequestWrapper 클래스 실행되면서 내용이 담김 (ContentCachingRequestWrapper 클래스 169열)
            chain.doFilter(httpServletRequest, httpServletResponse);
        */
        // Filter 단에서 ContentCachingRequestWrapper httpServletRequest(캐시)를 만들어서 chain.doFilter(httpServletRequest, httpServletResponse);에
        // 넣어주면 똑같은 프로젝트 interceptor에서는 request를
        // (ContentCachingRequestWrapper)request;로 형 변환 시켜줄 수 있음
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())  // query를 가져와야지 parsing됨
                .build()
                        .toUri();

        log.info("request url : {}", url);

        // 권한 체크(@Auth 있는지 체크)
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작을 하는데
        // 단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키, requestparameter의 특정 값을 보는 정책 가짐
        if(hasAnnotation){
            // 권한 체크
            String query = uri.getQuery();
            log.info("query :{}", query);
            if(query.equals("name=steve")){
                return true;
            }
            return false;
        }

        // return false; 여서 이 이후로 controller까지 못감
//        return false;
        return true;
    }

    public boolean checkAnnotation(Object handler, Class clazz){

        // resource javascript, html 통과 시켜줘야함
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation 이 있을 때는 true
            return true;
        }

        return false;

    }
}
