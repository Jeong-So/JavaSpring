package com.example.put.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

//@JasonProperty : 일괄적으로 한가지 변수에 이름을 지어주는 Annotation
//@JsonNaming(Value = PropertyNamingStrategy.SnakeCaseStrategy.class) : 해당 클래스는 object mapper라는 모듈 동작 시 smake_case로 인식
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PutRequestDto {

    private String name;
    private int age;
    private List<CarDto> carList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarDto> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDto> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "PutRequestDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carList=" + carList +
                '}';
    }
}

/* JSON
{
    "name" : "",
    "age" : ,
    "carList" : [
        {
            "name" : "",
            "carNumber" : ""
         },
         {
            "name" : "",
            "carNumber" : ""
         }
     ]
}

{
    "name" : "",
    "age" : ,
    "car_list" : [
        {
            "name" : "",
            "car_number" : ""
         },
         {
            "name" : "",
            "car_number" : ""
         }
     ]
}

 */
