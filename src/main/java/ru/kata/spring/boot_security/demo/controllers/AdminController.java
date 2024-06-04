package ru.kata.spring.boot_security.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;
import ru.kata.spring.boot_security.demo.util.UserValidator;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;
    private final UserValidator userValidator;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImp userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/admin";
    }

    @GetMapping(value = "/addUser")
    public String add(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users",userService.getAllUsers());
        return "admin/addUser";
    }


    @PostMapping("/addUser")
    public String add(Model model, @RequestBody User user,@RequestParam BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            return "admin/addUser";
        }
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return  "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String edit( Model model, @RequestParam Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users",userService.getAllUsers());
        return "admin/editUser";
    }

    @PatchMapping("/{id}")
    public String update(@RequestBody User user, @RequestParam Long id){
        userService.updateUser(id,user);
        return "redirect:/admin";
    }


    @DeleteMapping("/delete/{id}")
    private String delete(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
