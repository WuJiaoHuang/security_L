package com.wu.secur;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wu.secur.mapper")
public class SecurApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurApplication.class, args);
    }

}
