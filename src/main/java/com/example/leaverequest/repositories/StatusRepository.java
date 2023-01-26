package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.leaverequest.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{
    
}
