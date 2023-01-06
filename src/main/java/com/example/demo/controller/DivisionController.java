package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.tools.DbConnection;
import com.example.demo.models.Division;

@Controller
@RequestMapping("division")
public class DivisionController {
    private DivisionDAO ddao = new DivisionDAO(DbConnection.getConnection());
    
    // GET DATA
    @GetMapping
    public String index(Model model) {
        model.addAttribute("divisions", ddao.getAll());
        return "division/index";
    }

    // @GetMapping
    // public boolean insertDivision(Model model) {
    //     model.addAttribute("divisions", ddao.insert(null));
    //     return true;
    // }
    @GetMapping(value = {"form", "form/{divisionId}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String getById(@PathVariable(required = false) Integer divisionId, Model model){
        if (divisionId != null) {
            model.addAttribute("division", ddao.getById(divisionId));
        } else {
            model.addAttribute("division", new Division());
        }

        return "division/form";
    }
    
}
