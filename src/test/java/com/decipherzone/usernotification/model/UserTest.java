package com.decipherzone.usernotification.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest extends EntityId {
    String name, email, phone;
    User user;

    @BeforeEach
    void setUp() {
        name= "Ricky Martin";
        email = "rickymartin@gmail.com";
        phone = "1234567890";
        user = new User(name,email,phone);
    }

    @Test
    void getName() {
        assertEquals(name,user.getName());
    }

    @Test
    void getEmail() {
        assertEquals(email,user.getEmail());
    }

    @Test
    void getPhone() {
        assertEquals(phone,user.getPhone());
    }
}