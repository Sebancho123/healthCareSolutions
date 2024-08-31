package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Paciente;
import com.medicina.healthCareSolutions.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository iPaciRepo;

    @Override
    public List<Paciente> findAll() {
        return iPaciRepo.findAll();
    }

    @Override
    public Paciente save(Paciente paciente) {
        return iPaciRepo.save(paciente);
    }

    @Override
    public String deleteById(Long id) {
        iPaciRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return iPaciRepo.findById(id);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return iPaciRepo.save(paciente);
    }

    //metodo para decir cuanto tiempo lleva creado el paciente asi como caundo una postea algo xd
    //*esto va en el service pero buu
    @Override
    public String getElapsedTime(LocalDateTime fechacrea, LocalDateTime fechaActual) {

        long years = ChronoUnit.YEARS.between(fechacrea, fechaActual);

        if (years > 0) {
            return years == 1 ? "hace 1 año" : "hace " + years + " años";
        }

        long months = ChronoUnit.MONTHS.between(fechacrea, fechaActual);
        if (months > 0) {
            return months == 1 ? "hace 1 mes" : "hace " + months + " meses";
        }

        long weeks = ChronoUnit.WEEKS.between(fechacrea, fechaActual);
        if (weeks > 0) {
            return weeks == 1 ? "hace 1 semana" : "hace " + weeks + " semanas";
        }

        long days = ChronoUnit.DAYS.between(fechacrea, fechaActual);
        if (days > 0) {
            return days == 1 ? "hace 1 día" : "hace " + days + " días";
        }

        long hours = ChronoUnit.HOURS.between(fechacrea, fechaActual);
        if (hours > 0) {
            return hours == 1 ? "hace 1 hora" : "hace " + hours + " horas";
        }

        long minutes = ChronoUnit.MINUTES.between(fechacrea, fechaActual);
        if (minutes > 0) {
            return minutes == 1 ? "hace 1 minuto" : "hace " + minutes + " minutos";
        }

        long seconds = ChronoUnit.SECONDS.between(fechacrea, fechaActual);
        return seconds == 1 ? "hace 1 segundo" : "hace " + seconds + " segundos";
    }

    @Override
    public List<Paciente> getPaciSegAnio(int anio) {

        List<Paciente> pacienteList = this.findAll();
        List<Paciente> newPacienteList = new ArrayList<>();

        for (Paciente paciente : pacienteList) {
            int anioOfCreat = paciente.getFecha_creacion().getYear();

            if (anioOfCreat == anio){
                newPacienteList.add(paciente);
            }

        }

        return newPacienteList;
    }

    @Override
    public List<Paciente> getPaciSegMes(int anio, int mes) {

        List<Paciente> pacienteList = this.findAll();
        List<Paciente> newPacienteList = new ArrayList<>();

        for (Paciente paciente : pacienteList) {

            int anioOfCreat = paciente.getFecha_creacion().getYear();
            int mesDeCreation = paciente.getFecha_creacion().getMonthValue();

            System.out.println("mes " + mesDeCreation);

            if (anioOfCreat == anio && mesDeCreation == mes) {
                 newPacienteList.add(paciente);
            }

        }

        return newPacienteList;
    }

    @Override
    public List<Paciente> getPaciSegDay(int anio, int mes, int day) {

        List<Paciente> pacienteList = this.findAll();
        List<Paciente> newPacienteList = new ArrayList<>();

        for (Paciente paciente : pacienteList) {

            int anioOfCreat = paciente.getFecha_creacion().getYear();
            int mesDeCreation = paciente.getFecha_creacion().getMonthValue();
            int dayDeCreation = paciente.getFecha_creacion().getDayOfMonth();

            if(anioOfCreat == anio && mesDeCreation == mes && dayDeCreation == day) {
                newPacienteList.add(paciente);
            }
        }

        return newPacienteList;
    }
}
