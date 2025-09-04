package com.personal.auth_service;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.personal.domain"})
@EnableJpaRepositories(basePackages = {"com.personal.repository"})
@ComponentScan(basePackages = {
        "com.personal.auth_service",
//        "com.personal.repository",
        "com.personal.service"
})
@SpringBootApplication
@RequiredArgsConstructor
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}

