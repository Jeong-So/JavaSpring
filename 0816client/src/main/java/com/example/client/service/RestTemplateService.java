package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // 1. Get
    //요청
    //http://localhost:9090/api/server/hello 호출
    //response 받아옴
    public UserResponse hello(){  // String -> UserResponse

        // get로 보낼 때 방법
        // 1. 주소 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","tomas")
                .queryParam("age", 53)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());  // 주소 잘 만들어졌는지 확인

        // 2. 응답을 무엇으로 받을지만 지정
        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class); // getForObject : response 받을 형태 지정
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);
        // 43, 44 같은 것 (header 이름이나 상세 정보 받기 위해선 ResponseEntity<UserResponse>로 받으면 좋음
        // getForEntity : get은 http의 get을 뜻함 Entity로 가져오겠다

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody(); // result.getBody()가 UserResponse 형태
    }

    // 2. post
    //http://localhost:9090/api/server/user/{userId}/name/{userName}
    // user 등록시키는 것을 만듬
    public void post(){   // UserResponse(1) --> void(2)

        // post로 보낼 때 방법
        // 1. 주소 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")// expand : 순서대로 {}와 매칭
                .toUri();
        System.out.println(uri);

        // 2. 보내고 싶은 request body 데이터 json으로 만드는게 아니라 object로 만들어서 보냄
        // post기 때문에 http body가 있어야 함 (우리는 object만 보냄)
        // object mapper가 json으로 바꿔서 rest template에서 http body에 json으로 넣어줌
        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        // 3. 응답을 무엇으로 받을지만 지정
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class); // 1
        //해당주소(uri)에 request body(req)를 만들어서 응답은 UserResponse 클래스로 받을거야
        ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class);  // (@) 서버가 어떤 식으로 내려줄 지 모르니까 일단 String으로 찍겠다

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

//        return response.getBody();  // 1

    }

    public UserResponse exchange(){

        // 1. 주소 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")// expand : 순서대로 {}와 매칭
                .toUri();
        System.out.println(uri);

        // 2. 보내고 싶은 request body 데이터 json으로 만드는게 아니라 object로 만들어서 보냄
        // post기 때문에 http body가 있어야 함 (우리는 object만 보냄)
        // object mapper가 json으로 바꿔서 rest template에서 http body에 json으로 넣어줌
        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity  // 보낼때는 RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","fffff")
                .body(req);

        // 3. 응답을 무엇으로 받을지만 지정
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();


    }

    // body 내용이 바뀌는 경우 (Header은 같음) 재사용가능하게 하는 것
    public Req<UserResponse> genericExchange(){

        // 1. 주소 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")// expand : 순서대로 {}와 매칭
                .toUri();
        System.out.println(uri);

        // 2. 보내고 싶은 request body 데이터 json으로 만드는게 아니라 object로 만들어서 보냄
        // http body -> object -> object mapper -> json -> rest template -> http body json

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<>();
        req.setHeader(
            new Req.Header()
        );

        req.setResBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity  // 보낼때는 RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","fffff")
                .body(req);

        // 3. 응답을 무엇으로 받을지만 지정
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){});
        // generic에는 .class 붙을 수 없음 ( Req<UserResponse>.class--> new ParameterizedTypeReference<Req<UserResponse>>(){} )

        return response.getBody();  // 첫 getBody() : response의 getBody(), response안의 body 읽는거
    }

    /*
JSON 형태에서 BODY가 바뀔경우
REQ 안에 REQ가 하나 더 들어감
{
    "header" : {
        "response_code" : "OK"
    },
    "body" : {
        "book" : "spring boot",
        "page" : 1024
    }
}

{
    "header" : {
        "response_code" : "OK"
    },
    "body" : {
        "name" : "steve",
        "age" : 10
    }
}

 */

}
