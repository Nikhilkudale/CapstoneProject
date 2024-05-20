package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.User;

@SpringBootTest
public class UsersTest {

    @Test
    public void testUserCreation() {
        String username = "john_doe";
        String password = "password123";
        String email = "john@example.com";
        Date registrationDate = new Date();

        User user = new User(username, password, email, registrationDate);

        // Verify user object is not null
        assertNotNull(user);

        // Verify user properties
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(registrationDate, user.getRegistrationDate());
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();

        String username = "jane_doe";
        String password = "pass123";
        String email = "jane@example.com";
        Date registrationDate = new Date();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRegistrationDate(registrationDate);

        // Verify user properties using getters
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(registrationDate, user.getRegistrationDate());
    }

    @Test
    public void testUserDefaultConstructor() {
        User user = new User();

        // Verify default values
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getRegistrationDate());
    }
}
