package com.example.leaverequest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.dto.DivisionDTO;
import com.example.leaverequest.models.Division;
import com.example.leaverequest.services.DivisionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/division")
public class RestDivisionController {

    private DivisionService divisionService;

    @Autowired
    public RestDivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping("getAll")
    public List<Division> getAll() {

        return divisionService.getAll();
    }

    @GetMapping("test")
    public String test() {

        return "test";
    }

    @GetMapping("getAllDivision")
    public List<DivisionDTO> getAllDivision() {

        return divisionService.getAllDivision();
    }

}
