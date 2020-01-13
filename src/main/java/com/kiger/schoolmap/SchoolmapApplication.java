package com.kiger.schoolmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SchoolmapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolmapApplication.class, args);
    }

}
