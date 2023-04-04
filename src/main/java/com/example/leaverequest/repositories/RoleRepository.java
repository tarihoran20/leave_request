package com.example.leaverequest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

@Query(value="select t.id from role t WHERE t.level = (select max(level) from role)", nativeQuery = true)
int getMaxLevelById();

@Query(value="SELECT r.name FROM role r JOIN user u on r.id = u.role_id join employee e on e.id=u.id WHERE e.id = ?1", nativeQuery = true)
String getRoleNameByEmployeeId(Integer id);
    
}
