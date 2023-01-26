package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer>{
    
}
