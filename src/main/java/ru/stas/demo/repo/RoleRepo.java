package ru.stas.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stas.demo.model.Role;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
