package ru.gaer390.web.service;

import ru.gaer390.web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByEmail(String email);
    void deleteUser(Long id);
    void addNewUser(User user);
    void updateUser(User user, String password);
    void addRoleToUser(String email, String nameRole);
}
