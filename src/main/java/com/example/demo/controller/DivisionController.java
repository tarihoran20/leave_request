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

    // @GetMapping
    // public boolean insertDivision(Model model) {
    //     model.addAttribute("divisions", ddao.insert(null));
    //     return true;
    // }
    @GetMapping(value = {"form", "form/{Id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String getById(@PathVariable(required = false) Integer Id, Model model){
        if (Id != null) {
            model.addAttribute("division", ddao.getById(Id));
        } else {
            Division division = new Division();
            Region region = new Region();
            division.setName("Distribution");
            region.setId(27);
            
            model.addAttribute("division", region.getId());
            model.addAttribute("division", new Division());
        }

        return "division/form";
    }

    @PostMapping(value = {"delete/{Id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable int Id){
        Boolean result = ddao.delete(Id);

        if(result){
            return "redirect:/division";
        } else {
            return "delete failed";
        }
    }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Division division, Region region){
        Boolean result;
        
        if (division.getId() == null) {
            result = ddao.insert(division, region);
        } else {
            result = ddao.update(division);
        }

        if(result){
            return "redirect:/division";
        } else {
            return "region/form";
        }
    }
    
}
