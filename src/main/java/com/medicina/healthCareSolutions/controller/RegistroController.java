package com.medicina.healthCareSolutions.controller;

import com.medicina.healthCareSolutions.model.Registro;
import com.medicina.healthCareSolutions.service.IRegistroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regis")
@PreAuthorize("isAuthenticated()")
public class RegistroController {

    @Autowired
    private IRegistroService iRegSer;

    @GetMapping("/getAll")
    public ResponseEntity<List<Registro>> findAll() {

        System.out.println("hola craft");

        List<Registro> registroList = iRegSer.findAll();
        return ResponseEntity.ok(registroList);

    }

    @PostMapping("/save")
    public ResponseEntity<Registro> save(@RequestBody Registro registro){

        Registro newRegistro = iRegSer.save(registro);
        return ResponseEntity.ok(newRegistro);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Registro> deleteById(@PathVariable Long id) {

        try{
            iRegSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Registro> findById(@PathVariable Long id) {

        Optional<Registro> registro = iRegSer.findById(id);
        return registro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    public ResponseEntity<Registro> update(@RequestBody Registro registro) {

        Registro newRegistro = iRegSer.update(registro);
        return ResponseEntity.ok(newRegistro);

    }

}
