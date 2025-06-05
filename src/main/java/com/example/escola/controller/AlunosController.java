package com.example.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.escola.model.AlunosModel;
import com.example.escola.service.AlunosService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alunos")
public class AlunosController {
    
    @Autowired
    private AlunosService service;

    @GetMapping
    public List<AlunosModel> listartodos(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunosModel> buscarPorID(@PathVariable Long id){
        return service.buscarPorId(id)
               .map(ResponseEntity :: ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AlunosModel salvar(@RequestBody AlunosModel alunosModel){
        return service.salvar(alunosModel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AlunosModel> atualizar(@PathVariable Long id, @RequestBody AlunosModel alunosModel){
       
        if(!service.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        alunosModel.setId(id);

        return ResponseEntity.ok(service.salvar((alunosModel)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<AlunosModel> deletar(@PathVariable Long id){

        if(!service.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(id);
        
        return ResponseEntity.noContent().build();
    }
}
