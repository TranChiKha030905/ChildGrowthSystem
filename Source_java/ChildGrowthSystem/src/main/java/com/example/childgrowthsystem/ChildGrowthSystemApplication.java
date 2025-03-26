package com.example.childgrowthsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChildGrowthSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildGrowthSystemApplication.class, args);
    }

}
