package com.example.leaverequest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaverequest.configuration.MyUserDetails;
import com.example.leaverequest.dto.ApprovalDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.services.EmployeeService;
import com.example.leaverequest.services.StatusService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/approvalhr")
public class RestApprovalHrController {

    private StatusService statusService;
    private EmployeeService employeeService;
    private MyUserDetails userDetails;

    @Autowired
    public RestApprovalHrController(StatusService statusService, EmployeeService employeeService, MyUserDetails userDetails){
        this.statusService = statusService;
        this.employeeService = employeeService;
        this.userDetails = userDetails;
    }

    // @GetMapping("/getAllRequested")
    // public List<Object> getAll(ApprovalDTO approvalDTO, String email){
    //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //     email = auth.getName();

    //     List<Object> temp = new ArrayList<>();
    //     List<Object> tempra = new ArrayList<>();

    //     for (Integer a : statusService.getAllRequestIdByEmployeeId(employeeService.getIdByEmail(email))) {
    //         tempra.add(statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)));
    //     }


    //     if (tempra.isEmpty() == false) { //jika approved/rejected tidak ada maka tampil requested

    //         for (Integer a : statusService.getAllRequestIdByEmployeeId(employeeService.getIdByEmail(email))) {

    //             if (statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false && statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false) {
    //                 temp.remove(a);
    //             }
    //             else if(statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false && statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == true) {
    //                 temp.add(statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)));
    //             }
    //         }
           
    //     } else {
    //         temp = null;
    //     }

    //     return temp;

    // }

    @GetMapping("/getAllRequested")
    public List<ApprovalDTO> getAll(ApprovalDTO approvalDTO, String email){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        email = auth.getName();

        List<ApprovalDTO> list_ap = new ArrayList<ApprovalDTO>();

        List<Object> temp = new ArrayList<>();
        List<Object> tempra = new ArrayList<>();

        Integer id_employee = employeeService.getIdByEmail(userDetails.getUsername());

        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("HR"))) {
            for (Integer a : statusService.getAllRequestIdByEmployeeId(employeeService.getIdByEmail(email))) {
                tempra.add(statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)));
            }
    
    
            if (tempra.isEmpty() == false) { //jika approved/rejected tidak ada maka tampil requested tempra: temporary reject approve
    
                for (Integer a : statusService.getAllRequestIdByEmployeeId(employeeService.getIdByEmail(email))) {
    
                    if (statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false && statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false) {
                        temp.remove(a);
                    }
                    else if(statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == false && statusService.getAllRejectedApprovedByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email)).isEmpty() == true) {
        
                        List<Request> list_r = statusService.getAllRequestByRequestIdAndEmployeeId(a, employeeService.getIdByEmail(email));
                        list_r.forEach((r) -> {
                            ApprovalDTO ap = new ApprovalDTO();
                            Status s = statusService.getStatusByRequestId(r.getId());
                            ap.setRequest_id(r.getId());
                            ap.setDate(s.getDate());
                            ap.setStart_date(r.getStart_date());
                            ap.setEnd_date(r.getEnd_date());
                            ap.setDescription(r.getDescription());
                            ap.setEmployee_approver(employeeService.getNameById(s.getEmployee_id().getId()));
                            ap.setEmployee_requester((employeeService.getNameById(s.getEmployee_request_id().getId())));

                            ap.setStatus(s.getStatus());
                            list_ap.add(ap);
                    });
                    return list_ap;
                    }
                }
               
            } else {
                return list_ap;
            }
        }

        

        return null;

    }


    
    @PutMapping("/responseRejected/{request_id}")
    public ResponseEntity<Object> insertRejected(@PathVariable(required = true) Integer request_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Request request = new Request();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Boolean result1;
        Status status = new Status();
        
                request.setId(request_id);  //request_id
                status.setRequest(request);

                status.setDate(java.time.LocalDate.now()); //date

                employee.setId(statusService.getEmployeeIdRequesterByStatusId(statusService.getStatusIdByRequestId(request_id))); //requester
                status.setEmployee_request_id(employee);
                
                employee2.setId(employeeService.getIdByEmail(email)); //approver
                status.setEmployee_id(employee2);

                status.setStatus("Rejected"); //status
                
                result1 = statusService.save(status);


       if(result1) {
        return new ResponseEntity<>("Response from HR Submitted", HttpStatus.OK);
        }
        else {
            return ResponseEntity.badRequest().body("FAILED Response from HR Submitted");
        }
    }

    @PutMapping("/responseApproved/{request_id}")
    public ResponseEntity<Object> insertApproved(@PathVariable(required = true) Integer request_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Request request = new Request();
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();
        Boolean result1, result2;
        Status status = new Status();  
        Status status2 = new Status();
    
            request.setId(request_id);  //request_id
            status.setRequest(request);

            status.setDate(java.time.LocalDate.now()); //date

            employee.setId(statusService.getEmployeeIdRequesterByStatusId(statusService.getStatusIdByRequestId(request_id))); //requester
            status.setEmployee_request_id(employee);
            
            employee2.setId(employeeService.getIdByEmail(email)); //approver
            status.setEmployee_id(employee2);

            status.setStatus("Approved");
            
            result1 = statusService.save(status);


            if(result1) {
                return new ResponseEntity<>("Response from HR Submitted", HttpStatus.OK);
            }
            else {
                return ResponseEntity.badRequest().body("FAILED Response from HR Backup Submitted");
            }

    }

    
}
