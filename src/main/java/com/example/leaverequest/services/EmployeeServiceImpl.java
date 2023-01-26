package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.User;
import com.example.leaverequest.repositories.EmployeeRepository;
import com.example.leaverequest.repositories.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RoleService roleService;

    // @Autowired
    // PasswordEncoder encoder;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Boolean save(Employee employee) {
        // Employee employee = new Employee();
        // employee.setFullName(registerDTO.getFullName());
        // employee.setBirthDate(registerDTO.getBirthDate());
        // employee.setEmail(registerDTO.getEmail());
       
        employeeRepository.save(employee);

        return employeeRepository.findById(employee.getId()).isPresent();
    }


    // @Override
    // public Boolean findAccount(String email, String password){
    //     Employee employee = employeeRepository.findEmailAndPassword(email, password);
        

    //     //return employeeRepository.findEmailAndPassword(email, password);
    //     return encoder.matches(password, employee.getUser().getPassword());
    // }

    @Override
    public Boolean findAccountByEmailDTO(LoginDTO loginDTO) {
            Employee employee = employeeRepository.findByEmail(loginDTO.getEmail());
            User user = new User();
            if(employee == null){
                throw new RuntimeException("Email does not exist.");
            }
            if(!user.getPassword().equals(loginDTO.getPassword())){
                throw new RuntimeException("Password wrong!");
            }
        // return encoder.matches(loginDTO.getPassword(), user.getPassword());
        return true;
    }

    @Override
    public Integer getIdByEmail(String Email){
        return employeeRepository.findIdByEmail(Email);
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Division Tidak ditemukan"));
    }

    @Override
    public Boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }

    @Override
    public Integer getIdEmployeeByEmail(String email){
        return employeeRepository.getIdByEmail(email);
    }


}
