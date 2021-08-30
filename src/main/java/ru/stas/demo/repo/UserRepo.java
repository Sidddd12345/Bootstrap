package ru.stas.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stas.demo.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}
