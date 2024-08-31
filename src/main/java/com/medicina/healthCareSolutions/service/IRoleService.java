package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    public List<Role> getAll();

    public Role save(Role role);

    public String deleteById(Long id);

    public Optional<Role> findById(Long id);

    public Role update(Role role);

}
