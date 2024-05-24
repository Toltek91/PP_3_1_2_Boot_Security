package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Optional;


@Controller
@RequestMapping(("/user"))
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String userGet(@RequestParam Long id, Model model) {
        Optional<User> user = userService.findById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
