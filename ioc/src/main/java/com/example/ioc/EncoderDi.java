package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// #1 iocApplication

@Component
public class EncoderDi {

    private IEncoder iEncoder;

    // 에러발생 iEncoder을 implement한 클래스가 2개 이상이여서
    // Spring에서 어떤걸 매칭할지 몰라서 에러 발생
    // 해결 : @Qualifier("클래스 이름")
    public EncoderDi(@Qualifier("url_encoder") IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    // Spring에서 Bean을 주입 받을 수 있는 장소
    // 1. 변수 생성자   2. set method
    public void setiEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }

}



// #3 iocApplication
/*
public class EncoderDi {

    private IEncoder iEncoder;

    // 에러발생 iEncoder을 implement한 클래스가 2개 이상이여서
    // Spring에서 어떤걸 매칭할지 몰라서 에러 발생
    // 해결 : @Qualifier("클래스 이름")
    public EncoderDi(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    // Spring에서 Bean을 주입 받을 수 있는 장소
    // 1. 변수 생성자   2. set method
    public void setiEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }

}

 */