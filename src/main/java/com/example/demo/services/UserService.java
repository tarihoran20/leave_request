package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface UserService {
    public List<User> getAll();
    public Boolean save(User user, Employee employee);
    // public User findUserByPassword(String password);
    
}
