package ru.stas.demo.service;

import org.springframework.stereotype.Service;
import ru.stas.demo.model.Role;
import ru.stas.demo.model.User;
import ru.stas.demo.repo.RoleRepo;
import ru.stas.demo.repo.UserRepo;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class DataBaseInit {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public DataBaseInit(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void initDB() {
        Role adminRole = new Role(1L, "ROLE_ADMIN");
        Role userRole = new Role(2L, "ROLE_USER");
        roleRepo.save(adminRole);
        roleRepo.save(userRole);
        User user = new User();
        user.setId(1);
        user.setAge(10);
        user.setUsername("admin");
        user.setEmail("admin@mail.ru");
        user.setLastName("matveev");
        user.setName("stas");
        user.setPassword("$2a$12$XxQJ5KwZ3RsYPBnvEVuxSObQCnKQIdGUUPyCFIpNoAR0d75NfDHHC");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ROLE_ADMIN"));
        user.setRoles(roleSet);
        userRepo.save(user);
    }
}
