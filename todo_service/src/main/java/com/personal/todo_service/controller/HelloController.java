package com.personal.todo_service.controller;

import com.personal.todo_service.util.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/todos/users")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        Long userId = AuthUtil.getUserId();
        List<String> roles = AuthUtil.getRoles();
        return "Hello user " + userId + " with roles " + roles;
    }

}
