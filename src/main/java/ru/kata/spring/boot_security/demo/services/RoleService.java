package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    Role getRoleById(long id);

    void addRole( Role role);
}
