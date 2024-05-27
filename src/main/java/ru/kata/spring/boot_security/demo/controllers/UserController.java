package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUser(Model model, Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("users", userService.getUserById(id));
        return "user/user";
    }

}
