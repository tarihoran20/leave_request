package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.tools.DbConnection;
import com.example.demo.models.Division;
import com.example.demo.models.Region;

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

    @GetMapping(value = {"form", "form/{id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String form(@PathVariable(required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("division", ddao.getById(id));
            model.addAttribute("regions", ddao.getAllRegion());
        } else {
            Division division = new Division();

            model.addAttribute("division", new Division());
            model.addAttribute("regions", ddao.getAllRegion());
        }

        return "division/form";
    }

    @PostMapping(value = {"delete/{id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable Integer id){
        Boolean result = ddao.delete(id);

        if(result){
            return "redirect:/division";
        } else {
            return "delete failed";
        }
    }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Division division){
        Boolean result;
        
        if (division.getId() == null) {
            result = ddao.insert(division);
        } else {
            result = ddao.update(division);
        }

        if(result){
            return "redirect:/division";
        } else {
            return "division/form";
        }
    }
    
}
