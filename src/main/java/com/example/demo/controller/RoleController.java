package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Role;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "role/index";
    }

    @PostMapping(value = {"delete/{id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable Integer id){
        Boolean result = roleService.delete(id);

        if(result){
            return "redirect:/role";
        } else {
            return "delete failed";
        }
    }

    @GetMapping(value = {"form", "form/{id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String form(@PathVariable(required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("role", roleService.getById(id));
        } else {
            model.addAttribute("role", new Role());
        }

        return "role/form";
    }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Role role){
        Boolean result = roleService.save(role);
        
        if(result){
            return "redirect:/role";
        } else {
            return "role/form";
        }
    }

}
