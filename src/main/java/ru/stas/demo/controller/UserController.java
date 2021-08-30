package ru.stas.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stas.demo.model.User;
import ru.stas.demo.service.RoleService;
import ru.stas.demo.service.UserService;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("user", userService.listUsers());
        model.addAttribute("roles",roleService.getRoles());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User person) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam(value = "role", required = false) String[] role,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";
        if(role == null){
            return "users/new";
        }

        roleService.setRoles(user,role);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam(value = "role", required = false) String[] role) {
        if (bindingResult.hasErrors())
            return "users/edit";
        if(role == null){
            return "users/edit";
        }
        roleService.setRoles(user,role);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(userService.findById(id));
        return "redirect:/admin/users";
    }
}
