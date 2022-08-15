package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j  // log를 남기기 위함
@Component  // Spring에 대해 Bean으로 관리 필요
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 전처리
        // filter단 : request와 response에 대해서 변경 가능
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // ContentCachingRequestWrapper의 cachedContent는 길이만 담고 있지 내용은 없음 (ContentCachingRequestWrapper 클래스 80열)
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);  // 이 클래스 사용 시 몇번이고 읽을 수 있음
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        String url = httpServletRequest.getRequestURI(); // 어떤 주소 요청했는지 값 들어있음

//        BufferedReader br = httpServletRequest.getReader();
//
//        br.lines().forEach(line -> {
//            log.info("url : {}, line : {}", url, line);
//        });
        // JAVA : br.read(); 커서 단위로 읽으며 커서가 끝으로 감
        // 오류 발생 : 이미 Spring이 forEach를 통해서 JSON BODY를 다 읽어버린 상태
        // 이 상태에서 ApiController의 User 객체를 통해 body를 읽으려고 봤더니 이미 내용을 읽어버려서 읽을 수 없음
        // 이 문제 해결을 위해 ContentCachingRequestWrapper 클래스 사용하여 캐싱하며
        // 내용을 읽기 위해선 doFilter 이후에 읽어줘야함

        // 2. 메인
        // doFilter을 해야지 ContentCachingRequestWrapper 클래스 실행되면서 내용이 담김 (ContentCachingRequestWrapper 클래스 169열)
        chain.doFilter(httpServletRequest, httpServletResponse);

        // 3. 후처리
        // doFilter 일어난 후 req 정보 읽기
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, request body : {} ", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        log.info("response status : {}, response body : {}", httpStatus, resContent);
    }
}
