package com.personal.service.impl;


import com.personal.domain.AppUser;
import com.personal.model.AppUserModel;
import com.personal.repository.AppUserRepository;
import com.personal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;


    public void testRepository() {
        System.out.println("User count: " + appUserRepository.count());
    }
    @Override
    public String addUser(AppUserModel appUserModel) {
        AppUser user = new AppUser();
        user.setUserName(appUserModel.getUsername());
        user.setPassword(appUserModel.getPassword());
        appUserRepository.save(user);
        return "User added successfully";
    }



    @Override
    public AppUser getUser(String username) {
        AppUser user = appUserRepository.findByUserName(username)
                .orElseThrow(()-> new NoSuchElementException("User not found"));
        return user;
    }
}
