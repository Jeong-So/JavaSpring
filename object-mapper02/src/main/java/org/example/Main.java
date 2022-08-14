package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.dto.Car;
import org.example.dto.User;

import java.lang.runtime.ObjectMethods;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
//        System.out.println("Hello world!");

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // JsonNode의 값 가져오기
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : " + _name);
        System.out.println("age : " + _age);

        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars;  // ArrayNode로 타입 변환

        // convertValue : object를 가지고 우리가 원하는 class로 맵핑을 시킬 수 있음
        // TypeReference에는 우리가 원하는 generic 타입을 넣어주면 됨
        // object(arrayNode)를 받아서 우리가 원하는 타입(List<Car>)으로 바꿔줌
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>(){});
        System.out.println(_cars);

        //jsonNode.set()은 막아놈 jsonNode라는 객체 클래스에서는 특정 노드의 값을 바꿀 수 없도록 해놈
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");  // name 을 홍길동 -> steve
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
    }

}