package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

@Query(value="select t.id from role t WHERE t.level = (select max(level) from role)", nativeQuery = true)
int getMaxLevelById();
    
}
