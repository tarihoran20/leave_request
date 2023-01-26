package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leaverequest.models.Region;
import com.example.leaverequest.services.RegionService;

@Controller
@RequestMapping("region")
public class RegionController {
    //private RegionDAO rdao = new RegionDAO(DbConnection.getConnection());

    @Autowired
    private RegionService regionService;

    // Get Data
    @GetMapping
    public String index(Model model) {
        //Object region = rdao.getAll();
        model.addAttribute("regions", regionService.getAll());
        return "region/index";
    }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Region region){
        Boolean result = regionService.save(region);
        
        if(result){
            return "redirect:/region";
        } else {
            return "region/form";
        }
    }

    @PostMapping(value = {"delete/{id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable Integer id){
        Boolean result = regionService.delete(id);

        if(result){
            return "redirect:/region";
        } else {
            return "delete failed";
        }
    }

    @GetMapping(value = {"form", "form/{id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String form(@PathVariable(required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("region", regionService.getById(id));
        } else {
            model.addAttribute("region", new Region());
        }

        return "region/form";
    }
}
