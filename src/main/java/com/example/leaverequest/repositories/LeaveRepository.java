package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

  

    
    
}
