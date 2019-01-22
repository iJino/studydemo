package com.liangjinhai.studydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudydemoApplication.class, args);
    }

}

