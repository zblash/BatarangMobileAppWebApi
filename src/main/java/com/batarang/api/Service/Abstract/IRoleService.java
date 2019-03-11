package com.batarang.api.Service.Abstract;

import com.batarang.api.Model.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role Add(Role role);

    void Delete(Role role);

    Role Update(Role role, Role updatedRole);
}
