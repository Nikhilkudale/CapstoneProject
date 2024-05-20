
package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.InvalidUserException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Exceptions.UsernameAlreadyExistsException;
import com.example.demo.entity.User;
import com.example.demo.repo.usersrepo;

@Service
public class UsersserviceImplementation {

    private final usersrepo usersRepository;

    @Autowired
    public UsersserviceImplementation(usersrepo usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean checkUsername(String username) {
        return usersRepository.existsByUsername(username);
    }
    
    
    public Long addUser(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new InvalidUserException("User data is invalid or incomplete");
        }
        
        if (usersRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
        
        User savedUser = usersRepository.save(user);
        return savedUser.getUserId(); // Return the generated user ID
    }

    public boolean validate(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidUserException("Username or password cannot be null");
        }
        
        if (usersRepository.existsByUsername(username)) {
            User user = usersRepository.getByUsername(username);
            String dbPassword = user.getPassword();
            return password.equals(dbPassword);
        } else {
            return false;
        }
    }

    public User getByUsername(String username) {
        User user = usersRepository.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(Long userId) {
        User user = usersRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        return user;
    }
    
    ////====================//
    
 

}