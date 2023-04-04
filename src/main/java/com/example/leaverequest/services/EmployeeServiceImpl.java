package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.leaverequest.dto.ApprovalDTO;
import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.Status;
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


    @Autowired 
    private StatusService statusService;

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

    @Override
    public Employee getCurrentEmployee(String name) {
        
        return employeeRepository.findByUsername(name);
    }

    @Override
    public Integer getManagerIdByEmail(String email) {
        return employeeRepository.getManagerIdByEmail(email);
    }

    @Override
    public Integer getEmployeeHrId(){
        return employeeRepository.getEmployeeHrId();
    }

    @Override
    public String getNameById(Integer id){
        return employeeRepository.getNameById(id);
    }

    @Override
    public List<Employee> getEmployeeBackupList(Integer employee_request_id) {
        Integer emp1 = employee_request_id;
        Integer emp2 = employee_request_id;

        return employeeRepository.getEmployeeBackupList(emp1, emp2);
    }


}
