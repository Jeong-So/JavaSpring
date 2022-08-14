package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("hello");

        // Text JSON --> Object
        // Object --> Text JSON

        // controller req Json(text) --> Object
        // response object --> json(text)

        // ObjectMapper는 Class의 get Method 참조 따라서 Class에 getter 필요 (object --> text)
        // ObjectMapper은 동작하기 위해 default 생성자 필요 (text --> object)
        var objectMapper = new ObjectMapper();

        // object --> text
        // object mapper get method 를 활용
        var user = new User("steve", 10, "010-1111-2222");
        String text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text --> object
        // ObjectMapper은 동작하기 위해 default 생성자 필요
        User objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);

    }

}
