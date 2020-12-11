package com.example.timedtask2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.timedtask2.mapper")
public class Timedtask2Application {

    public static void main(String[] args) {
        SpringApplication.run(Timedtask2Application.class, args);
    }

}
