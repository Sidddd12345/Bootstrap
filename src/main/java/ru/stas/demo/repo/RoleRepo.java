package ru.stas.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stas.demo.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}
