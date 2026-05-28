package com.example.bai1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Bai1Application {

    public static void main(String[] args) {
        SpringApplication.run(Bai1Application.class, args);
    }

}
