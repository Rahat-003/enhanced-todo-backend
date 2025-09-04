package com.personal.service.impl;


import com.personal.domain.User;
import com.personal.model.UserModel;
import com.personal.repository.UserRepository;
import com.personal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public void testRepository() {
        System.out.println("User count: " + userRepository.count());
    }
    @Override
    public String addUser(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        userRepository.save(user);
        return "User added successfully";
    }



    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new NoSuchElementException("User not found"));
        return user;
    }
}
