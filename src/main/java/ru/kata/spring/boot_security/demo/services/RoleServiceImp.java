package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Service

public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
       return roleRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(long id) {
        return roleRepository.getById(id);
    }


    @Override
    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public RoleRepository getRoleRepository(){
        return roleRepository;
    }
}
