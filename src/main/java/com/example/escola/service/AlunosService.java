package com.example.escola.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.escola.model.AlunosModel;
import com.example.escola.repository.AlunosRepository;

@Service
public class AlunosService {
    
    @Autowired
    private AlunosRepository repository;

    public List<AlunosModel> listarTodos(){
        return repository.findAll();
    }

    public Optional<AlunosModel> buscarPorId(Long id){
        return repository.findById(id);
    }

    public AlunosModel salvar (AlunosModel alunosModel){
        return repository.save(alunosModel);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


}
