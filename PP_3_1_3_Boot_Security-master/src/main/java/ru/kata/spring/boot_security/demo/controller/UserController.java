package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "/admin")
    public String printUsers(Model model){
        List<User> userList  = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin";
    }

    @RequestMapping(value = "/admin/new")
    public String addNewUser(Model model){
        User user = new User();
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("newUser", user);
        model.addAttribute("roleList", roleList);
        return "new-user";
    }

    @RequestMapping(value = "/admin/saveNewUser")
    public String saveNewUser(@ModelAttribute("newUser") User user){
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit-user/{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUser(id);
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("editUser", user);
        model.addAttribute("roleList", roleList);
        return "edit-user";
    }

    @PostMapping(value = "/admin/update-user")
    public String updateUser(User user){
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @RequestMapping("/user")
    public String getUserPage(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        List<Role> userRoles = user.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "user";
    }

    @RequestMapping("/user/{id}")
    public String showUserProfile(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        List<Role> userRoles = user.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", userRoles);
        return "user";
    }

    @RequestMapping("/logout")
    public String logOut() {
        return "redirect:/login";
    }

    @RequestMapping("/page403")
    public String showErrorPage() {
        return "page403";
    }

}
