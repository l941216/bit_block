package com.bluebooth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


@ComponentScan
@MapperScan("com.bluebooth.mapper")
@EnableAsync
@SpringBootApplication
public class BlueboothApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueboothApplication.class, args);
    }
}
