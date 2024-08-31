package com.medicina.healthCareSolutions.service;

import com.medicina.healthCareSolutions.model.UserSec;
import com.medicina.healthCareSolutions.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{

    @Autowired
    private IUserSecRepository iUserRepo;

    @Override
    public List<UserSec> getAll() {
        return iUserRepo.findAll();
    }

    @Override
    public UserSec save(UserSec userSec) {
        return iUserRepo.save(userSec);
    }

    @Override
    public String deleteById(Long id) {
        iUserRepo.deleteById(id);
        return "delete success";
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return iUserRepo.findById(id);
    }

    @Override
    public UserSec update(UserSec userSec) {
        return this.save(userSec);
    }

    @Override
    public String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
