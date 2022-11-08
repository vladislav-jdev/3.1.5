package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    User saveOrUpdate(User user);

    List<User> getAllUsers();

    User getUser(Long id);

    void deleteUser(Long id);

    User findByUsername(String username);

}
