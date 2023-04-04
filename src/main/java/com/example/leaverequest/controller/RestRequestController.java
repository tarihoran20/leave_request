package com.example.leaverequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RequestDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Leave;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.RequestService;
import com.example.leaverequest.services.StatusService;
import com.example.leaverequest.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/request")
public class RestRequestController {
    private RequestService requestService;
    private StatusService statusService;
    private UserService userService;
    private EmployeeService employeeService;
    private MyUserDetails myUserDetails;

    @Autowired
    public RestRequestController(RequestService requestService, StatusService statusService, UserService userService,
            EmployeeService employeeService, MyUserDetails myUserDetails) {
        this.requestService = requestService;
        this.statusService = statusService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.myUserDetails = myUserDetails;
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody RequestDTO requestDTO, LoginDTO loginDTO) {
        Boolean requestSave, statusSave;
        Request request = new Request();
        Leave leave = new Leave();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Status status = new Status();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

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

        requestSave = requestService.save(request);

        status.setDate(java.time.LocalDate.now()); // date
        status.setStatus("Requested ..");

        employee2.setId(requestDTO.getEmployee_backup_id()); // employee approval
        status.setEmployee_id(employee2);

        request.setId(request.getId()); // request_id
        status.setRequest(request);

        employee.setId(employeeService.getIdByEmail(myUserDetails.getUsername())); // requester
        status.setEmployee_request_id(employee);

        statusSave = statusService.save(status);

        // return new ResponseEntity<>("Request Submitted", HttpStatus.OK);
        if (requestSave && statusSave) {
            return ResponseEntity.ok().body("200 Request Submitted");
        } else {
            return ResponseEntity.badRequest().body("400 / 500 Request Failed (Error Request / Server)");
        }
    }

}
