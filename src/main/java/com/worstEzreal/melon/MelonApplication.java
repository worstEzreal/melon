package com.worstEzreal.melon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = "com.worstEzreal.melon")
public class MelonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelonApplication.class, args);
    }

}
