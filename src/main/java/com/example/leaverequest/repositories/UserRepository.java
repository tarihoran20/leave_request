package com.example.leaverequest.repositories;

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


   
    // User findByPassword(String password);
}
