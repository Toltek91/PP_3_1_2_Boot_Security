package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleService roleService;


    public AdminController(UserService userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getRoles());
        return "addUser";
    }

    @PostMapping("admin/new")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/edit")
    public String editUser(@RequestParam Long id, Model model){
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/addUser";
    }


    @PatchMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "/addUser";
        }

        userService.updateUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete")
    public String delete (@RequestParam (value="id", required = false) long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
