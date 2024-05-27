package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.CustomUserDetailService;


@Component
public class UserValidator implements Validator {

    private final  CustomUserDetailService customUserDetailService;

    @Autowired
    public UserValidator(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            customUserDetailService.loadUserByUsername(user.getUsername());
        }catch (UsernameNotFoundException u){
            return;
        }

        errors.rejectValue("name", "Пользователь с таким именем уже существует!");

    }
}
