package com.example.leaverequest.controller;

import java.time.Duration;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RequestDTO;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.LeaveService;
import com.example.leaverequest.services.RequestService;

@Controller
@RequestMapping("request")
public class RequestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private RequestService requestService;
    
    @GetMapping(value =  {"form"})
    public String form(Model model) {
        
        model.addAttribute("request", new RequestDTO());
        model.addAttribute("leaves", leaveService.getALL());

        return "request/form";
    }

    @PostMapping("save")
    public String save(RequestDTO requestDTO, LoginDTO loginDTO){
        Request request = new Request();
        Status status = new Status();

        Integer leave = requestService.getLeaveByEmail(loginDTO.getEmail());
        //long days = Duration.between(requestDTO.getStart_date().toInstant(), requestDTO.getEnd_date().toInstant()).toDays();
        
        // if (requestDTO.getLeave_id() == 1) {
        //     if (days > leave) {
        //         requestService.save(request);
        //         return "usermanagement/requested";
        //     } else {
        //         return "requested/form";
        //     }
        // }
        // else  {
            
        // }
        // request.setStart_date(requestDTO.getStart_date());
        // request.setEnd_date(requestDTO.getEnd_date());
        // request.setTemp_phone_number(requestDTO.getTemp_phone_number());
        // request.setTemp_address(requestDTO.getTemp_address());
        // request.setLeave(requestDTO.getLeave());
        // request.setEmployee(requestDTO.getEmployee());

        // requestService.save(request);

        // status.setDate(java.time.LocalDate.now());
        // status.setStatus("Requested");
        // status.setEmployee(requestDTO.getEmployee());
        // status.setRequest(requestDTO.getId());


        return "usermanagement/dashboard";
    }
}
