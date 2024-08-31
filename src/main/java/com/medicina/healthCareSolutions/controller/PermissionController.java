package com.medicina.healthCareSolutions.controller;

import com.medicina.healthCareSolutions.model.Permission;
import com.medicina.healthCareSolutions.service.IPermissionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
@PreAuthorize("denyAll()")
public class PermissionController {

    @Autowired
    private IPermissionService iPerSer;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Permission>> findAll() {

        List<Permission> permissionList = iPerSer.getAll();
        return ResponseEntity.ok(permissionList);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> save(@RequestBody Permission permission) {

        Permission newPermission = iPerSer.save(permission);
        return ResponseEntity.ok(newPermission);

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> deleteById(@PathVariable Long id) {

        try {
            iPerSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Permission> findById(@PathVariable Long id) {

        Optional<Permission> permission = iPerSer.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> update(Permission permission) {

        Permission newPermission = iPerSer.update(permission);
        return ResponseEntity.ok(newPermission);
    }


}
