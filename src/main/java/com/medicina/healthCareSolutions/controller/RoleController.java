package com.medicina.healthCareSolutions.controller;

import com.medicina.healthCareSolutions.model.Permission;
import com.medicina.healthCareSolutions.model.Role;
import com.medicina.healthCareSolutions.service.IPermissionService;
import com.medicina.healthCareSolutions.service.IRoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/role")
@PreAuthorize("denyAll()")
public class RoleController {

    @Autowired
    private IRoleService iRolSer;

    @Autowired
    private IPermissionService iPerSer;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Role>> getAll() {
        List<Role> roleList = iRolSer.getAll();
        return ResponseEntity.ok(roleList);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> save(@RequestBody Role role) {

        Set<Permission> permissions = new HashSet<>();

        for (Permission permission : role.getPermissionList()) {

            Permission readPermission = iPerSer.findById(permission.getId()).orElse(null);
            if(readPermission != null){
                permissions.add(permission);
            }

        }

        if(!permissions.isEmpty()){
            role.setPermissionList(permissions);
            Role newRole = iRolSer.save(role);
            return ResponseEntity.ok(newRole);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> deleteById(@PathVariable Long id) {

        try {
            iRolSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Role> findById(@PathVariable Long id) {

        Optional<Role> role = iRolSer.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> update(@RequestBody Role role){

        Role newRole = iRolSer.update(role);
        return ResponseEntity.ok(newRole);

    }

}
