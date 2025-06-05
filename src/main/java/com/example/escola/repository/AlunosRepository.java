package com.example.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.escola.model.AlunosModel;


@Repository
public interface AlunosRepository extends JpaRepository<AlunosModel, Long>{
}
