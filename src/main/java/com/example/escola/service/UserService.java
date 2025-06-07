package com.example.escola.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.escola.model.UserModel;
import com.example.escola.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel salvar(UserModel user) {
        return repository.save(user);
    }

    public Optional<UserModel> buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<UserModel> login(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }
}
