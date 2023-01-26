package com.example.leaverequest.services;

import java.util.List;

import org.springframework.ui.Model;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;

public interface EmployeeService {
    public List<Employee> getAll();
    public Boolean save(Employee employee);
    // public Boolean findAccount(String email, String password);
    public Boolean findAccountByEmailDTO(LoginDTO loginDTO);
    public Integer getIdByEmail(String email);
    public Employee getById(Integer id);
    public Boolean delete(Integer id);
    public Integer getIdEmployeeByEmail(String email);
    //public Employee findIdByEmail(String email);

}
