package com.personal.service;

import com.personal.domain.User;
import com.personal.model.UserModel;

public interface UserService {
    String addUser(UserModel userModel);

    User getUser(String username);
}
