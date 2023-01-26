package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.leaverequest.daos.RegionDAO;
import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Role;
import com.example.leaverequest.models.User;
import com.example.leaverequest.repositories.EmployeeRepository;
import com.example.leaverequest.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired 
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

    // @Autowired
    // PasswordEncoder encoder;


    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public Boolean save(User user){
        // User user = new User();
        // Employee employee = new Employee();
        // user.setId(employee.getId());

        // Role role = new Role();
        // role.setId(roleService.getLevelById());
        // user.setRole(role);

        // user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);
            
        return userRepository.findById(user.getId()).isPresent();
    }



    // @Override
    // public User findUserByPassword(String password) {
        
    //     return userRepository.findByPassword(password);
    // }

    // @Override
    // public Object login(LoginDTO loginDTO) {
    //     Employee employee = employeeRepository.findEmail(loginDTO.getEmail());
    //     if(loginDTO.getEmail() != null && encoder.matches(loginDTO.getPassword(), employee.getUser().getPassword())){
    //         return loginDTO;
    //     }

    //     return false;
    // }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void updatePasswordEmployee(Integer id, String Password){
        userRepository.updatePasswordEmployee(id, Password);
    }

    
}
