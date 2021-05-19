package com.decipherzone.usernotification.service;

import com.decipherzone.usernotification.model.EntityId;
import com.decipherzone.usernotification.model.User;
import com.decipherzone.usernotification.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImpTest extends EntityId {

    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImp(userRepository);

    }

    @Test
    void verify_getAllUsers() {
        User user = new User();
        List userList = new ArrayList();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void verify_getUser() {
        User user = new User();
        user.setId(101L);
        when(userRepository.findById(101L)).thenReturn(java.util.Optional.of(user));
        assertEquals(user, userService.getUser(101L));

    }

    @Test
    void verify_createUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userRepository.save(user));
    }

}