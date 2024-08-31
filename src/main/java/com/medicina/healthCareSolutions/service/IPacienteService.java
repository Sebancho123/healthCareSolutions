package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    public List<Paciente> findAll();

    public Paciente save(Paciente paciente);

    public String deleteById(Long id);

    public Optional<Paciente> findById(Long id);

    public Paciente update(Paciente paciente);

    public String getElapsedTime(LocalDateTime fechacrea, LocalDateTime fechaActual);

    public List<Paciente> getPaciSegAnio(int anio);

    public List<Paciente> getPaciSegMes(int anio, int mes);

    public List<Paciente> getPaciSegDay(int anio, int mes, int day);
}
