package com.example.leaverequest.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.leaverequest.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update user SET password = ?2 WHERE id = ?1",nativeQuery = true)
    void updatePasswordEmployee(Integer id, String password);


    @Query(value = "select e.*, u.*, d.*, r.* from user u join employee e on u.id = e.id join division d on e.division_id = d.id join role r on u.role_id = r.id where e.id = ?1", nativeQuery = true)
    List<User> getAllDataEmployeeById(Integer employee_id);

    @Query(value = "select e.*, u.*, d.*, r.* from user u join employee e on u.id = e.id join division d on e.division_id = d.id join role r on u.role_id = r.id where e.id = ?1", nativeQuery = true)
    User getAllDataEmployeeById2(Integer employee_id);

    
    // User findByPassword(String password);
}
