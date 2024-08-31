package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Role;
import com.medicina.healthCareSolutions.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository iRolRepo;

    @Override
    public List<Role> getAll() {
        return iRolRepo.findAll();
    }

    @Override
    public Role save(Role role) {
        return iRolRepo.save(role);
    }

    @Override
    public String deleteById(Long id) {
        iRolRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Role> findById(Long id) {
        return iRolRepo.findById(id);
    }

    @Override
    public Role update(Role role) {
        return this.save(role);
    }
}
