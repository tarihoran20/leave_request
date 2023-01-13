package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Division;
import com.example.demo.services.DivisionService;
import com.example.demo.services.RegionService;

@Controller
@RequestMapping("division")
public class DivisionController {
    
    @Autowired
    private DivisionService divisionService;
    
    @Autowired
    private RegionService regionService;

    // GET DATA
    @GetMapping
    public String index(Model model) {
        model.addAttribute("divisions", divisionService.getAll());
        return "division/index";
    }

    @GetMapping(value = {"form", "form/{id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String form(@PathVariable(required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("division", divisionService.getById(id));
            model.addAttribute("regions", regionService.getAll());
        } else {
            model.addAttribute("division", new Division());
            model.addAttribute("regions", regionService.getAll());
        }

        return "division/form";
    }

    @PostMapping(value = {"delete/{id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable Integer id){
        Boolean result = divisionService.delete(id);

        if(result){
            return "redirect:/division";
        } else {
            return "delete failed";
        }
    }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Division division){
        Boolean result = divisionService.save(division);
        
        if(result){
            return "redirect:/division";
        } else {
            return "division/form";
        }
    }
    
}
