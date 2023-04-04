package com.example.leaverequest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.EmployeeAccountDTO;
import com.example.leaverequest.dto.EmployeeBackupDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/employeeAccount")
public class RestEmployeeAccountController {

    private MyUserDetails userDetails;
    private UserService userService;
    private EmployeeService employeeService;

    @Autowired
    public RestEmployeeAccountController(MyUserDetails userDetails, UserService userService,
            EmployeeService employeeService) {
        this.userDetails = userDetails;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("getData")
    public ResponseEntity<EmployeeAccountDTO> getData(EmployeeAccountDTO employeeAccountDTO, String email) {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // email = auth.getName();

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Employee")) ||
                userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Manager"))) {

            User dataUser = userService
                    .getAllDataEmployeeById2(employeeService.getIdByEmail(userDetails.getUsername()));

            EmployeeAccountDTO dEmployeeDTO = new EmployeeAccountDTO();

            dEmployeeDTO.setId(dataUser.getId());
            dEmployeeDTO.setNik(dataUser.getEmployee().getNik());
            dEmployeeDTO.setFullname(dataUser.getEmployee().getFullname());
            dEmployeeDTO.setEmail(dataUser.getEmployee().getEmail());
            dEmployeeDTO.setPhone_number(dataUser.getEmployee().getPhone_number());
            dEmployeeDTO.setAddress(dataUser.getEmployee().getAddress());
            dEmployeeDTO.setJoin_date(dataUser.getEmployee().getJoin_date());
            dEmployeeDTO.setRemaining_leave(dataUser.getEmployee().getRemaining_leave());
            dEmployeeDTO.setRole(dataUser.getRole().getName());
            dEmployeeDTO.setDivision(dataUser.getEmployee().getDivision().getName());
            dEmployeeDTO.setManager(dataUser.getEmployee().getDivision().getEmployee().getFullname());

            return ResponseEntity.ok(dEmployeeDTO);
        }

        return null;
    }

    @GetMapping("getEmployeeBackupList")
    public List<EmployeeBackupDTO> getAll(Integer employee_request_id) {

        List<EmployeeBackupDTO> listEmployeeBackup = new ArrayList<EmployeeBackupDTO>();

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Employee"))
                || userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Manager"))) {
            employee_request_id = employeeService.getIdByEmail(userDetails.getUsername());

            List<Employee> listEmployee = employeeService.getEmployeeBackupList(employee_request_id);
            listEmployee.forEach((e) -> {

                EmployeeBackupDTO empB = new EmployeeBackupDTO();

                empB.setId(e.getId());
                empB.setFullname(e.getFullname());

                listEmployeeBackup.add(empB);

            });

            return listEmployeeBackup;
        }
        return null;
    }

}
