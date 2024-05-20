package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;

public interface usersrepo extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User getByUsername(String username);

    User getByUsernameAndPassword(String username, String password);
}
