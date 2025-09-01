package com.personal.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/todo-service")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Todo Service!";
    }
}
