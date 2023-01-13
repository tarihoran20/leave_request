package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

@Query(value="select t.id from tb_m_role t WHERE t.level = (select max(level) from tb_m_role)", nativeQuery = true)
int getMaxLevelById();
    
}
