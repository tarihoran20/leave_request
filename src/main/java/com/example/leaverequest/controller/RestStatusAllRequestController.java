package com.example.leaverequest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.HistoryDTO;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.StatusService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/statusallrequest")
public class RestStatusAllRequestController {

    private StatusService statusService;
    private EmployeeService employeeService;
    private MyUserDetails userDetails;

    @Autowired
    public RestStatusAllRequestController(StatusService statusService, EmployeeService employeeService,
            MyUserDetails userDetails) {
        this.statusService = statusService;
        this.employeeService = employeeService;
        this.userDetails = userDetails;
    }

    @GetMapping("getAll")
    public List<HistoryDTO> getAll(HistoryDTO historyDTO, String email) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // email = auth.getName();
        email = userDetails.getUsername();

        List<HistoryDTO> listHistory = new ArrayList<HistoryDTO>();

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Employee")) || 
        userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Manager"))) {
            // for (Integer a : statusService.getAllRequestIdByEmployeeId(employeeService.getIdByEmail(email))) {
                List<Status> listStatus = statusService.getAllStatusByEmployeeId(employeeService.getIdByEmail(email)); //mengambil semua data status berdasarkan id (getIdByEmail)
                listStatus.forEach((s) -> {
                    HistoryDTO hDTO = new HistoryDTO(); //penampung data
                    Request r = statusService.getDataRequestByRequestId(s.getRequest().getId()); //mengambil data request berdasarkan request id di table status

                    //data-data yang ingin ditampilkan berdasarkan penampung (DTO)
                    hDTO.setId(s.getId());
                    hDTO.setRequest_id(s.getRequest().getId());
                    hDTO.setDate(s.getDate());
                    hDTO.setStart_date(r.getStart_date());
                    hDTO.setEnd_date(r.getEnd_date());
                    hDTO.setDescription(r.getDescription());
                    hDTO.setEmployee_approver(employeeService.getNameById(s.getEmployee_id().getId()));
                    hDTO.setEmployee_requester((employeeService.getNameById(s.getEmployee_request_id().getId())));
                    hDTO.setStatus(s.getStatus());

                    listHistory.add(hDTO);
                });
                return listHistory;
            // }

        }

        return null;
    }

}
