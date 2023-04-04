package com.example.leaverequest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.HistoryDTO;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.RoleService;
import com.example.leaverequest.services.StatusService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/history")
public class RestHistoryController {

    @Autowired
    StatusService statusService;

    @Autowired
    MyUserDetails userDetails;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;

    @GetMapping("getAll")
    public List<HistoryDTO> getHistory() {
        List<HistoryDTO> list_h = new ArrayList<HistoryDTO>();
        Integer id_employee = employeeService.getIdByEmail(userDetails.getUsername());
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Employee")) ||
                userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Manager"))) {
            List<Request> r_list = statusService.getRequestHistoryByEmployeeId(id_employee);
            r_list.forEach((r) -> {
                HistoryDTO h = new HistoryDTO();
                Status s = statusService.getStatusByRequestId(r.getId());

                h.setRequest_id(r.getId());
                h.setDate(s.getDate());
                h.setStart_date(r.getStart_date());
                h.setEnd_date(r.getEnd_date());
                h.setEmployee_requester((employeeService.getNameById(s.getEmployee_request_id().getId())));
                h.setEmployee_approver(employeeService.getNameById(s.getEmployee_id().getId()));
                // h.setRole_name((roleService.getRoleNameByEmployeeId(s.getEmployee_request_id().getId())));
                h.setDescription(r.getDescription());
                h.setStatus(s.getStatus());
                list_h.add(h);
            });
            return list_h;

        }
        return null;
    }

}
