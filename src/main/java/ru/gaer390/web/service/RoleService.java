package ru.gaer390.web.service;

import ru.gaer390.web.model.Role;

public interface RoleService {
    void addNewRole(Role nameRole);
    Role findByNameRole(String nameRole);
}
