package com.example.filter.dto;

import lombok.*;

// Lombok : 롬복을 사용하면 아래 annotaion을 사용해서
// Getter와 Setter 메소드 만들지 않아도 자동으로 만들어줌
//@Getter
//@Setter
@Data  // 이 annotation은 getter setter toString eqals hashcode 등 모두 관리
@NoArgsConstructor  // 생성자 User()
@AllArgsConstructor  // 생성자 User(String name, int age)
public class User {

    private String name;
    private int age;

}
