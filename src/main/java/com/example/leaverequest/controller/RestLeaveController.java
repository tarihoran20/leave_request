package com.example.leaverequest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.models.Leave;
import com.example.leaverequest.services.LeaveService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/leave")
public class RestLeaveController {

    private LeaveService leaveService;

    @Autowired
    public RestLeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("getAll")
    public List<Leave> getAll() {

        return leaveService.getALL();
    }

}
