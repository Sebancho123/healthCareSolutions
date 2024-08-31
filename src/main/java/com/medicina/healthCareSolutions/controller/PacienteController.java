package com.medicina.healthCareSolutions.controller;

import com.medicina.healthCareSolutions.model.Paciente;
import com.medicina.healthCareSolutions.model.Registro;
import com.medicina.healthCareSolutions.service.IPacienteService;
import com.medicina.healthCareSolutions.service.IRegistroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/paci")
@PreAuthorize("isAuthenticated()")
public class PacienteController {

    @Autowired
    private IPacienteService iPaciSer;

    @Autowired
    private IRegistroService iRegSer;

    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> findAll() {

        List<Paciente> pacienteList = iPaciSer.findAll();

        for (Paciente paciente : pacienteList) {

            LocalDateTime fechacrea = paciente.getFecha_creacion();
            LocalDateTime fechaActual = LocalDateTime.now();

            String elapsedTime = iPaciSer.getElapsedTime(fechacrea, fechaActual);
            System.out.println("Creado " + elapsedTime);
            paciente.setCant_time("Creado " + elapsedTime);
            iPaciSer.update(paciente);

        }

        return ResponseEntity.ok(pacienteList);

    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Paciente paciente) {

        Long regis_id = paciente.getUnRegistro().getId();

        Registro readRegistro = iRegSer.findById(regis_id).orElse(null);

        if(readRegistro != null) {

            Paciente newPaciente = iPaciSer.save(paciente);
            return ResponseEntity.ok(newPaciente);

        }

        return ResponseEntity.badRequest().body("el registro no existe!");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Paciente> deleteById(@PathVariable Long id) {

        try{
            iPaciSer.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {

        Optional<Paciente> paciente = iPaciSer.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update")
    public ResponseEntity<Paciente> update(@RequestBody Paciente paciente) {

        Paciente newPaciente = iPaciSer.update(paciente);
        return ResponseEntity.ok(newPaciente);
    }

    @GetMapping("/getByYear/{year}")
    public ResponseEntity<List<Paciente>> getPaciByYear(@PathVariable int year) {

        List<Paciente> pacienteList = iPaciSer.getPaciSegAnio(year);
        return ResponseEntity.ok(pacienteList);
    }

    @GetMapping("/getByYearMes/{year}/{mes}")
    public ResponseEntity<List<Paciente>> getPaciByYear(@PathVariable int year, @PathVariable int mes) {

        List<Paciente> pacienteList = iPaciSer.getPaciSegMes(year, mes);
        return ResponseEntity.ok(pacienteList);

    }

    @GetMapping("/getByYearMesDay/{year}/{mes}/{day}")
    public ResponseEntity<List<Paciente>> getPaciByYear(@PathVariable int year, @PathVariable int mes, @PathVariable int day) {

        List<Paciente> pacienteList = iPaciSer.getPaciSegDay(year, mes, day);
        return ResponseEntity.ok(pacienteList);

    }


}
