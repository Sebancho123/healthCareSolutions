package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Permission;
import com.medicina.healthCareSolutions.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{

    @Autowired
    private IPermissionRepository iPerRepo;

    @Override
    public List<Permission> getAll() {
        return iPerRepo.findAll();
    }

    @Override
    public Permission save(Permission permission) {
        return iPerRepo.save(permission);
    }

    @Override
    public String deleteById(Long id) {
        iPerRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return iPerRepo.findById(id);
    }

    @Override
    public Permission update(Permission permission) {
        return this.save(permission);
    }
}
