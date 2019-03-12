package com.batarang.api.Service.Concrete;

import com.batarang.api.Model.Role;
import com.batarang.api.Repository.RoleRepository;
import com.batarang.api.Service.Abstract.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Role Add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void Delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role Update(Role role, Role updatedRole) {
        role.setRoleName(updatedRole.getRoleName());
        role.setUsers(updatedRole.getUsers());
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName).orElseThrow(RuntimeException::new);
    }
}
