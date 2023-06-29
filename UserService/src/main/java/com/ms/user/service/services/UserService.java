package com.ms.user.service.services;

import com.ms.user.service.entities.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);
}
