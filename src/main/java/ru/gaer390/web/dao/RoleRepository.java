package ru.gaer390.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaer390.web.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRole(String nameRole);
}
