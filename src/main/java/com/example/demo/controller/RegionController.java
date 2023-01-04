package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.RegionDAO;
import com.example.demo.models.Region;
import com.example.demo.tools.DbConnection;

@Controller
@RequestMapping("region")
public class RegionController {
    private RegionDAO rdao = new RegionDAO(DbConnection.getConnection());

    // Get Data
    @GetMapping
    public String index(Model model) {
        //Object region = rdao.getAll();
        model.addAttribute("regions", rdao.getAll());
        return "region/index";
    }

    //CREATE
    //GET
    // /region/form
    @GetMapping(value = {"form"})
    public String create(Model model) {
        model.addAttribute("region", new Region());
        return "region/form";
    }
    //POST
    @PostMapping("save")
    public String save(Region region){
        Boolean result = rdao.insert(region);

        if(result){
            return "redirect:/region";
        } else {
            return "region/form";
        }
    }


   
}
