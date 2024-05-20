package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.entity.User;
import com.example.demo.repo.usersrepo;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepoTest {

    @Autowired
    private usersrepo usersRepo;

    @Test
    public void testSaveUser() {
        // Given
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");

        // When
        usersRepo.save(user);

        // Then
        User savedUser = usersRepo.getByUsername("testUser");
        assertNotNull(savedUser);
        assertEquals("password", savedUser.getPassword());
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    public void testSaveUserWithExistingUsername() {
        // Given
        User existingUser = new User();
        existingUser.setUsername("existingUser");
        existingUser.setPassword("existingPassword");
        existingUser.setEmail("existing@example.com");
        usersRepo.save(existingUser);

        User user = new User();
        user.setUsername("existingUser"); // Attempting to save with the same username

        // Expecting DataIntegrityViolationException when saving user with existing username
        assertThrows(DataIntegrityViolationException.class, () -> usersRepo.save(user));
    }

    @Test
    public void testSaveUserWithEmptyUsername() {
        // Given
        User user = new User();
        user.setUsername(""); // Empty username
        user.setPassword("password");
        user.setEmail("test@example.com");

        // Expecting DataIntegrityViolationException when saving user with empty username
        assertThrows(DataIntegrityViolationException.class, () -> usersRepo.save(user));
    }
}
