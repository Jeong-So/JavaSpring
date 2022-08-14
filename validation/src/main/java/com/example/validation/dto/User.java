package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class User {

    @NotBlank
    private String name;
    @Max(value = 90)
    private int age;
//    @Email
//    private String email;
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
//    private String phoneNumber;
//    //@Size(min = 6, max = 6 )
//    @YearMonth
//    private String reqYearMonth;  // yyyyMM

    @Valid  // Validation 사용하려면 꼭 Valid 붙여야 함
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

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

//    public String getReqYearMonth() {
//        return reqYearMonth;
//    }
//
//    public void setReqYearMonth(String reqYearMonth) {
//        this.reqYearMonth = reqYearMonth;
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    // dto 안에서 Validation 검사
    // 아래와 같이 validation 판단시 재활용 불가능
    // YearMonth Annotation을 만들어서 그것을 사용할 클래스에 아래 내용 삽입 (재활용 가능)
//    @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
//    public boolean isReqYearMonthValidation(){
//        System.out.println("assert true call");
//        try {
//            LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
//        }catch(Exception e){
//            return false;
//        }
//        return true;
//    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
//                ", email='" + email + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}

