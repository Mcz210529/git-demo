package com.mcz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mcz.mapper")
public class SrmManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrmManageApplication.class, args);
    }

}
