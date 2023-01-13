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
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private RoleService roleService;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Boolean save(Employee employee, User user) {

        //employee.setId(employee.getId());
        employeeRepository.save(employee);

        user.setId(employee.getId());
        //userRepository.save(user);

        Role role = new Role();
        user.setRole(role);
        role.setId(roleService.getLevelById());

        userRepository.save(user);

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    @Override
    public Boolean findAccount(Employee employee, User user, Integer id, String email) {

        if (employee.getEmail() == employeeRepository.getEmail(email)) {

            if(employee.getId() == user.getId() && user.getPassword() == employeeRepository.getPassword(id)) {
                return true;
            }
            
        } else {
            return false;
        }

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    // @Override
    // public Employee findUserByEmail(String email) {
    //     return employeeRepository.findByEmail(email);
    // }
    
}
