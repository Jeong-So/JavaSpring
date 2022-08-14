package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    // GetMapping 만드는 방법 1 (최신 방법)
    @GetMapping(path = "/hello")  // http://localhost:9090/api/get/hello 로 동작
     public String Hello(){
        return "Hello";
    }

    // GetMapping 만드는 방법 2
    @RequestMapping(path = "/hi", method = RequestMethod.GET)  // method에 속성 지정, get http://localhost:9090/api/get/hi
    public String hi(){
        return "hi";
    }


    // Path Variable 받기
    // http://localhost:9090/api/get/path-variable/{name}
    // # 1
    @GetMapping("path-variable/{name}")
    public String pathVariable(@PathVariable String name){
        System.out.println("PathVariable : " + name);
        return name;
    }

    // # 2
    // 개발 시 PathVariable 주소에는 name을 했는데 변수는 다르게 설정해해야 할때(pathName)
    @GetMapping("path-variable02/{name}")
    public String pathVariable02(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }


    // Query Parameter

    //https://www.google.com/search?q=intellij&sxsrf=ALiCzsZqjwlBdh-31QUbyNXEtWycXDZNTA%3A1659602242168&ei=QoXrYs3NCZy32roPtL-5aA&ved=0ahUKEwjN9vKQ5Kz5AhWcm1YBHbRfDg0Q4dUDCA4&uact=5&oq=intellij&gs_lcp=Cgdnd3Mtd2l6EAMyBAgjECcyBAgjECcyBAgjECcyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgsIABCABBCxAxCDATIFCAAQgAQyBQgAEIAEOgcIABBHELADSgQIQRgASgQIRhgAUNUDWMwFYOYKaAFwAXgAgAF8iAHqAZIBAzAuMpgBAKABAcgBCsABAQ&sclient=gws-wiz
//    search?q=intellij
//    &sxsrf=ALiCzsZqjwlBdh-31QUbyNXEtWycXDZNTA%3A1659602242168
//    &ei=QoXrYs3NCZy32roPtL-5aA
//    &ved=0ahUKEwjN9vKQ5Kz5AhWcm1YBHbRfDg0Q4dUDCA4
//    &uact=5
//    &oq=intellij
//    &gs_lcp=Cgdnd3Mtd2l6EAMyBAgjECcyBAgjECcyBAgjECcyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgsIABCABBCxAxCDATIFCAAQgAQyBQgAEIAEOgcIABBHELADSgQIQRgASgQIRhgAUNUDWMwFYOYKaAFwAXgAgAF8iAHqAZIBAzAuMpgBAKABAcgBCsABAQ
//    &sclient=gws-wiz

    // ?key=value&key=value&key=value

    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30

    // # 1 RequestParam이라는 Annotation 붙임
    @GetMapping(path = "query-param")
    public  String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

//        queryParam.get("name");

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+ " = " + entry.getValue()+ "\n");
        });

        return sb.toString();
    }

    // # 2 (명시적)
    @GetMapping("query-param02")
    public String queryParam02(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;

    }

    // # 3 (객체로 받음) dto_UserRequest
    @GetMapping("query-param03")
    public String queryParam03( UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();

    }

}




