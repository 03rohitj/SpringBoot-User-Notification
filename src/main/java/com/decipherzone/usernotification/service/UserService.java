package com.decipherzone.usernotification.service;

import com.decipherzone.usernotification.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(Long id);

    User createUser(User user);

    User updateUser(Long id,User user);

    void deleteUser(Long id);

    ArrayList<Long> getAllNotifications(Long id);
}
