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
import com.example.leaverequest.services.RoleService;
import com.example.leaverequest.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // Get Data
    @GetMapping
    public String index(Model model) {
        //Object region = rdao.getAll();
        model.addAttribute("users", userService.getAll());
        //model.addAttribute("role", roleService.getLevelById());
        return "user/index";
    }

    // @GetMapping(value = {"form"})
    // public String form(Model model) {

    //     model.addAttribute("user", new User());
    //     model.addAttribute("employee", new Employee());        
        
    //     return "user/form";
    // }

    // @PostMapping("save")
    // public String save(@Nullable User user, Employee employee){
    //     Boolean result;
        
    //     if (user.getId()==null) {
    //         result = userService.save(user, employee);
    //     } else {
    //         result = userService.save(user, employee);
    //     }
        
    //     if(result){
    //         return "redirect:/user";
    //     } else {
    //         return "user/form";
    //     }
    // }


    
}
