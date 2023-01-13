package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired 
    private RoleService roleService;


    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Override
    public Boolean save(User user, Employee employee){

        user.setId(employee.getId());

        Role role = new Role();
        user.setRole(role);
        //role.setId(1);
        role.setId(roleService.getLevelById());
        
        userRepository.save(user);

        //employee.setId(user.getId());
        //user.setId(employee.getId());

        //employeeRepository.save(employee);
        //userRepository.save(user);
        
        
        return userRepository.findById(user.getId()).isPresent();
    }

    // @Override
    // public User findUserByPassword(String password) {
        
    //     return userRepository.findByPassword(password);
    // }


    
}
