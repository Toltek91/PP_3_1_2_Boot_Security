package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }


}
