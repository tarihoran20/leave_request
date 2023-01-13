package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface EmployeeService {
    //Employee findUserByEmail(String email);
    public List<Employee> getAll();
    public Boolean save(Employee employee, User user);
    public Boolean findAccount(Employee employee, User user, Integer id, String email);

}
