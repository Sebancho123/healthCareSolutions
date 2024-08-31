package com.medicina.healthCareSolutions.repository;

import com.medicina.healthCareSolutions.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistroRepository extends JpaRepository<Registro, Long> {

}
