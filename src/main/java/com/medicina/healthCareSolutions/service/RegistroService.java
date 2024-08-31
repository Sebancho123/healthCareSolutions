package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.Registro;
import com.medicina.healthCareSolutions.repository.IRegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroService implements IRegistroService{

    @Autowired
    private IRegistroRepository iRegRepo;

    @Override
    public List<Registro> findAll() {
        return iRegRepo.findAll();
    }

    @Override
    public Registro save(Registro registro) {
        return iRegRepo.save(registro);
    }

    @Override
    public String deleteById(Long id) {
        iRegRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<Registro> findById(Long id) {
        return iRegRepo.findById(id);
    }

    @Override
    public Registro update(Registro registro) {
        return iRegRepo.save(registro);
    }
}
