package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("usermanagement")
public class UserManagementController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

   
    @GetMapping(value = {"login"})
    public String login(Model model, Employee employee, User user) {
        model.addAttribute("email", employeeService.getAll());
        model.addAttribute("password", userService.getAll());

        return "user/formlogin";
    }
    
    @GetMapping(value = {"form"})
    public String register(Model model) {

        model.addAttribute("usermanagement", new User());
        model.addAttribute("employee", new Employee()); 
        return "user/form";
    }

    @PostMapping("loginconfirm")
    public String loginconfirm(Model model, Employee employee, User user){
        Boolean result = false;

        if (result == true) {
            result = employeeService.findAccount(employee, user, employee.getId(), employee.getEmail());
            return "employee/index";
        } else {
            result = employeeService.findAccount(employee, user, employee.getId(), employee.getEmail());
            return "user/formlogin";
        }
    }
}
