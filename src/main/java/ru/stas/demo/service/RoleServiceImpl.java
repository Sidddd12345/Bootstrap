package ru.stas.demo.service;


import org.springframework.stereotype.Service;
import ru.stas.demo.model.Role;
import ru.stas.demo.model.User;
import ru.stas.demo.repo.RoleRepo;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    public void setRoles(User user, String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            if (role[i].equals("ROLE_ADMIN")) {
                roleSet.add(findAll().get(0));
            }
            if (role[i].equals("ROLE_USER")) {
                roleSet.add(findAll().get(1));
            }
        }
        user.setRoles(roleSet);
    }

    @PostConstruct
    public void initDB(){
        Role admin = new Role(1L,"ROLE_ADMIN");
        Role user = new Role(2L,"ROLE_USER");
        roleRepo.save(admin);
        roleRepo.save(user);
//        roleRepo.flush();

    }
}
