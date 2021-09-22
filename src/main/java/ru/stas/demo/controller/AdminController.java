package ru.stas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stas.demo.model.User;
import ru.stas.demo.service.RoleService;
import ru.stas.demo.service.RoleServiceImpl;
import ru.stas.demo.service.UserService;
import ru.stas.demo.service.UserServiceImpl;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("newUser", new User());
        return "admin/admin";
    }

    @PostMapping
    public String create(@ModelAttribute("newUser") @Valid User user,
                         @RequestParam(value = "new_roles", required = false) String[] role,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/admin";
        }
        roleService.setRoles(user, role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "edit_roles", required = false) String[] role,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/admin";
        }
        roleService.setRoles(user, role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(id);
        userService.deleteUser(userService.findById(id));
        return "redirect:/admin";
    }
}
