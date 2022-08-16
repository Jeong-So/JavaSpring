package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //요청
    //http://localhost:9090/api/server/hello 호출
    //response 받아옴
    public UserResponse hello(){  // String -> UserResponse
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","tomas")
                .queryParam("age", 53)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());  // 주소 잘 만들어졌는지 확인

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class); // getForObject : response 받을 형태 지정
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);
        // 31, 32 같은 것 (header 이름이나 상세 정보 받기 위해선 ResponseEntity<UserResponse>로 받으면 좋음

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody(); // result.getBody()가 UserResponse 형태
    }

}
