package ru.stas.demo.service;



import ru.stas.demo.model.User;

import java.util.List;


public interface UserService {
    List<User> listUsers();
    User findById(Long id);
    void save(User user);
    void update(User user);
    void delete(User user);
}