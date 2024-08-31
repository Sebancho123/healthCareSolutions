package com.medicina.healthCareSolutions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String apellidos;
    private String telefono;

    //@Temporal(TemporalType.TIME)
    private LocalDateTime fecha_creacion;

    private String cant_time;
    private boolean isOlder;

    @OneToOne
    @JoinColumn(name = "registro_id")
    private Registro unRegistro;

}
