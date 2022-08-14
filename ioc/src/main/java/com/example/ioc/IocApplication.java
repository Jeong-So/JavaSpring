package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        SpringApplication.run(IocApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();

        // # 1
        /*
        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        EncoderDi encoderDi = new EncoderDi(base64Encoder);
        String result = encoderDi.encode(url);
        System.out.println(result);

        encoderDi.setiEncoder(urlEncoder);
        String result2 = encoderDi.encode(url);
        System.out.println(result2);
         */


        // # 2 ioc
        EncoderDi encoderDi = context.getBean(EncoderDi.class);
        String result = encoderDi.encode(url);
        System.out.println(result);


        // # 3
        /*
        EncoderDi encoderDi = context.getBean("base64Encoder2", EncoderDi.class);
        String result = encoderDi.encode(url);
        System.out.println(result);

         */

    }

}

// # 3  (실제로는 이러헥 안씀)
/*
// @Configuration 한개의 클래스에서 여러개의 Bean 등록
// 여기서는 Base64Encoder, UrlEncoder 클래스 안씀
@Configuration
class AppConfig{

    @Bean("base64Encoder2")
    public EncoderDi encoder(Base64Encoder base64Encoder){
        return new EncoderDi(base64Encoder);
    }

    @Bean("urlEncoder")
    public EncoderDi encoder(UrlEncoder urlEncoder) {
        return new EncoderDi(urlEncoder);
    }

}

 */
