package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer>{
    
}
