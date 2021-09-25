package ru.stas.demo.service;

import ru.stas.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    void saveUser(User user);
    void deleteUser(User user);
    User findById(long id);
}
