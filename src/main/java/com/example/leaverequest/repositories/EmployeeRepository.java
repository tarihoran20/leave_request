package com.example.leaverequest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    @Query(value = "select e.id as id, e.fullname as fullname, e.email as email from employee e " +
    "join user u on e.id = u.id join role r on r.id = u.role_id  WHERE e.email = ?1 && u.password = ?2", nativeQuery = true)
    Employee findEmailAndPassword(String email, String password);

    @Query(value = "select * from employee e join user on e.id = u.id where e.email = ?1", nativeQuery = true)
    Employee findByEmail(String email);
    
    @Query(value = "SELECT e.id FROM employee e WHERE e.email = ?1", nativeQuery = true)
    public Integer findIdByEmail(String Email);

    @Query(value = "select e.email from employee e where e.email = ?1", nativeQuery = true)
    public Employee findEmail(String email);

    @Query(value = "select * from user u join employee e on u.id = e.id join role r on u.role_id = r.id where e.email = ?1", nativeQuery = true)
    public User loginUser(String email);

    @Query(value = "select * from employee e join user u on e.id = u.id join role r on u.role_id = r.id where e.email = ?1", nativeQuery = true)
    public Employee loginEmployee(String email);

    @Query(value = "SELECT id FROM employee WHERE email = ?", nativeQuery = true)
    Integer getIdByEmail(String email);

    

}
