package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.tools.DbConnection;

@Controller
@RequestMapping("division")
public class DivisionController {
    private DivisionDAO ddao = new DivisionDAO(DbConnection.getConnection());

    @GetMapping
    public String index(Model model) {
        model.addAttribute("division", ddao.getAll());
        return "";
    }

    // @GetMapping
    // public boolean insertDivision(Model model) {
    //     model.addAttribute("divisions", ddao.insert(null));
    //     return true;
    // }
    
}
