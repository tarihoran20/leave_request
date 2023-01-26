package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    
}
