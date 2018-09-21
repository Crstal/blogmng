package com.crystal.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.crystal.blog.dao.mapper")
public class AppStart {
    
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}
