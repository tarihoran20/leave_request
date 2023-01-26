package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leaverequest.models.Leave;
import com.example.leaverequest.services.LeaveService;

@Controller
@RequestMapping("leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    //GET DATA
    @GetMapping
    public String index(Model model){
        model.addAttribute("leaves", leaveService.getALL());

        return "leave/index";
    }

    //INSERT
    @PostMapping("save") // POST FOR INSERT AND UPDATE
    public String save(Leave leave){
        Boolean result = leaveService.save(leave);
        
        if(result){
            return "redirect:/leave";
        } else {
            return "leave/form";
        }
    }

    //SAVE AND UPDATE
    @GetMapping(value = {"form", "form/{id}"}) 
    public String form(@PathVariable(required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("leave", leaveService.getById(id));
        } else {
            model.addAttribute("leave", new Leave());
        }

        return "leave/form";
    }

    //DELETE
    @PostMapping(value = {"delete/{id}"}) //DELETE USING POST MAPPING
    public String delete(@PathVariable Integer id){
        Boolean result = leaveService.delete(id);

        if(result){
            return "redirect:/leave";
        } else {
            return "delete failed";
        }
    }
    
}
