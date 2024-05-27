package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);
    Optional<User> getUserById(Long id);

    void updateUser(User user);
}
