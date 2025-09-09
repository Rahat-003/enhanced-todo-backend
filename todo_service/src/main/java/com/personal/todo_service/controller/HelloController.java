package com.personal.todo_service.controller;

import com.personal.domain.AppUser;
import com.personal.todo_service.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos/users")
public class HelloController {
//    private final AuthUtil authUtil;

    @GetMapping("/hello")
    public String hello() {
        Long userId = AuthUtil.getUserId();
        List<String> roles = AuthUtil.getRoles();
        AppUser user = AuthUtil.getUser();
        log.info("UserName: " + user.getUserName() + " ID: " + user.getId());
        return "Hello user " + userId + " with roles " + roles;
    }

}
