package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //INSERT DATA
    //GET
    // /region/form
    // @GetMapping(value = {"form"})
    // public String create(Model model) {
    //     model.addAttribute("region", new Region());
    //     return "region/form";
    // }

    //POST
    // @PostMapping("save")
    // public String save(Region region){
    //     Boolean result = rdao.insert(region);
    //     result = rdao.update(region);
    
    //     if(result){
    //         return "redirect:/region";
    //     } else {
    //         return "region/form";
    //     }
    // }

    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Region region){
        Boolean result;
        
        if (region.getId() == null) {
            result = rdao.insert(region);
        } else {
            result = rdao.update(region);
        }

        if(result){
            return "redirect:/region";
        } else {
            return "region/form";
        }
    }

    // DELETE
    // @GetMapping("delete") // USING GET MAPPING
    // public String delete(@RequestParam int regionId){
    //     Boolean result = rdao.delete(regionId);

    //     if(result){
    //         return "redirect:/region";
    //     } else {
    //         return "region/index";
    //     }
    // }

    // @GetMapping("delete/{regionId}") // USING GET MAPPING
    // public String delete(Region region){
    //     Boolean result = rdao.delete(region);

    //     if(result){
    //         return "redirect:/region";
    //     } else {
    //         return "delete failed";
    //     }
    // }

    @PostMapping(value = {"delete/{Id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable int Id){
        Boolean result = rdao.delete(Id);

        if(result){
            return "redirect:/region";
        } else {
            return "delete failed";
        }
    }

    // EDIT
    // GET
    // @GetMapping("/edit/{regionId}")
	// public String edit(@PathVariable(required = false) int regionId, Model model) {
    //     Region region = new Region();
    //     model.addAttribute("region", rdao.getById(regionId));

	// 	return "region/form";
	// }

    //POST
    // @PostMapping("update")
    // public String update(Region region){
    //     Boolean result = rdao.update(region);

    //     if(result){
    //         return "redirect:/region";
    //     } else {
    //         return "region/formEdit";
    //     }
    // }

    @GetMapping(value = {"form", "form/{Id}"}) //GET FOR INSERT AND EDIT GET BY ID TO SAME FORM
    public String getById(@PathVariable(required = false) Integer Id, Model model){
        if (Id != null) {
            model.addAttribute("region", rdao.getById(Id));
        } else {
            model.addAttribute("region", new Region());
        }

        return "region/form";
    }
}
