package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    // Get Data
    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("users", userService.getAll());
        //model.addAttribute("roles", roleService.getAll());
        return "employee/index";
    }

    @GetMapping(value = {"form"})
    public String form(Model model) {

        model.addAttribute("employee", new Employee());        
        model.addAttribute("user", new User());

        return "employee/formregister";
    }
    
    // @PostMapping("save")
    // public String save(@Nullable Employee employee, User user){
    //     Boolean result;
        
    //     if (employee.getId()==null) {
    //         result = employeeService.save(employee, user);
    //     } else {
    //         result = employeeService.save(employee, user);
    //     }
        
    //     if(result){
    //         return "redirect:/employee";
    //     } else {
    //         return "employee/form";
    //     }
    // }

    
}
