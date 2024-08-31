package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Registro;

import java.util.List;
import java.util.Optional;

public interface IRegistroService {

    public List<Registro> findAll();

    public Registro save(Registro registro);

    public String deleteById(Long id);

    public Optional<Registro> findById(Long id);

    public Registro update(Registro registro);

}
