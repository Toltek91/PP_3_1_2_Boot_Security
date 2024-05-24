package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user";
    }


    @GetMapping("/user/put")
    public String putUserForm(@RequestParam Long id, Model model) {
        Optional<User> user =  userService.findById(id);
        model.addAttribute("user", user);
        return "/user-put";
    }

    @PostMapping("/user-put")
    public String put(User user) {
        userService.put(user);
        return "redirect:/users";
    }


    @GetMapping("/user/delete")
    public String delete(Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
