package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "select e.remaining_leave from employee e where e.email = ?1", nativeQuery = true)
    public Integer getLeaveByEmail(String email);
    
}
