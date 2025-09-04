package com.personal.service;

import com.personal.domain.AppUser;
import com.personal.model.AppUserModel;

public interface UserService {
    String addUser(AppUserModel appUserModel);

    AppUser getUser(String username);
}
