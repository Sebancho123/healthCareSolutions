package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    public List<Permission> getAll();

    public Permission save(Permission permission);

    public String deleteById(Long id);

    public Optional<Permission> findById(Long id);

    public Permission update(Permission permission);

}
