package com.childgrowth.tracking;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaRepositories("com.childgrowth.tracking.repository")
@EntityScan("com.childgrowth.tracking.model")
public class ChildGrowthSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChildGrowthSystemApplication.class, args);
    }
}
