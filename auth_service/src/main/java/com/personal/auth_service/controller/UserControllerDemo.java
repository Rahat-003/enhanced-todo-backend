package com.personal.auth_service.controller;


import com.personal.domain.User;
import com.personal.model.UserModel;
import com.personal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserControllerDemo {
    private final UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.addUser(userModel));
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }
}
