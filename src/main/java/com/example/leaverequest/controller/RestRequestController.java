package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RequestDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Leave;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.RequestService;
import com.example.leaverequest.services.StatusService;
import com.example.leaverequest.services.UserService;

@RestController
@RequestMapping("api/request")
public class RestRequestController {
    private RequestService requestService;
    private StatusService statusService;
    private UserService userService;

    @Autowired
    public RestRequestController(RequestService requestService, StatusService statusService, UserService userService){
        this.requestService = requestService;
        this.statusService = statusService;
        this.userService = userService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Object>insert(@RequestBody RequestDTO requestDTO, LoginDTO loginDTO) {
        Request request = new Request();
        Leave leave = new Leave();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Status status = new Status();

        request.setId(request.getId());
        request.setStart_date(requestDTO.getStart_date());
        request.setEnd_date(requestDTO.getEnd_date());
        request.setTemp_phone_number(requestDTO.getTemp_phone_number());
        request.setTemp_address(requestDTO.getTemp_address());
        request.setDescription(requestDTO.getDescription());

        leave.setId(requestDTO.getLeave_id());
        request.setLeave(leave);

        employee.setId(requestDTO.getEmployee_backup_id());// employee backup
        request.setEmployee(employee);

        requestService.save(request);

     
        status.setDate(java.time.LocalDate.now()); //date
        status.setStatus(requestDTO.getStatus()); //status

        employee2.setId(requestDTO.getEmployee_backup_id()); // employee approval
        status.setEmployee(employee2);


        request.setId(request.getId()); //request_id
        status.setRequest(request);

        //employee.setId(requestDTO.getEmployee_request_id()); // employee request
        Integer id = userService.getById(employeeService.getIdByEmail(dataLogin.getEmail()));
        employee.setId(loginDTO.getEmail());
        status.setEmployeeRequest(employee);

        statusService.save(status);

        return new ResponseEntity<>("Request Submitted", HttpStatus.OK);
    }
    
}
