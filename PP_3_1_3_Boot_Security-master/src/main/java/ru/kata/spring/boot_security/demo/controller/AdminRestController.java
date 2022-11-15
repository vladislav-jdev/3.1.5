package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@RestController
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/admin/roles/{id}")
    Role getRoleById(@PathVariable("id") long id){
        return roleService.getRoleById(id);
    }

    @GetMapping("admin/user")
    public User showUserName(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @GetMapping(value = "/admin/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/admin/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping(value = "/admin/users/new")
    public User createUser(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @PutMapping(value = "/admin/users/update")
    public User updateUser(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/admin/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}

