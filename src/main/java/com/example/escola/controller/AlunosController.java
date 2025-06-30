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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Alunos", description = "API de gerenciamento de alunos")
public class AlunosController {

    
    @Autowired
    private AlunosService service;

      @GetMapping
    @Operation(summary = "Lista todos os alunos", description = "Retorna uma lista completa de alunos cadastrados.")
    public List<AlunosModel> listartodos(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Retorna um aluno pelo seu ID ou 404 caso não exista.")
    public ResponseEntity<AlunosModel> buscarPorID(@PathVariable Long id){
        return service.buscarPorId(id)
               .map(ResponseEntity :: ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Salvar novo aluno", description = "Cadastra um novo aluno no sistema.")
    public AlunosModel salvar(@RequestBody AlunosModel alunosModel){
        return service.salvar(alunosModel);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente.")
    public ResponseEntity<AlunosModel> atualizar(@PathVariable Long id, @RequestBody AlunosModel alunosModel){
       
        if(!service.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        alunosModel.setId(id);

        return ResponseEntity.ok(service.salvar((alunosModel)));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir aluno", description = "Remove um aluno do sistema pelo ID.")
    public ResponseEntity<AlunosModel> deletar(@PathVariable Long id){

        if(!service.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(id);
        
        return ResponseEntity.noContent().build();
    }

}
