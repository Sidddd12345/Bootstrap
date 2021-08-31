package ru.stas.demo.service;


import org.springframework.stereotype.Service;
import ru.stas.demo.model.Role;
import ru.stas.demo.model.User;
import ru.stas.demo.repo.RoleRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    final
    RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public void setRoles(User user, String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            if(role[i].equals("ROLE_ADMIN")){
                roleSet.add(new Role(1L,"ROLE_ADMIN"));

            } else {
                roleSet.add(new Role(2L,"ROLE_USER"));
            }
            user.setRoles(roleSet);
        }
    }

//    @PostConstruct
//    public void setDate(){
//        Role role = new Role(1L,"ROLE_ADMIN");
//        Role role2 = new Role(2L,"ROLE_USER");
//        roleDao.save(role);
//        roleDao.save(role2);
//    }


}
