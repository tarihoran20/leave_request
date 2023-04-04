package com.example.leaverequest.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ApprovalDTO {
    // private Integer id;
    private Integer request_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    // private String leave;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    
    private String employee_requester;
    private String employee_approver;
    private String description;
    private String status;
    // public Integer getId() {
    //     return id;
    // }
    // public void setId(Integer id) {
    //     this.id = id;
    // }
    public Integer getRequest_id() {
        return request_id;
    }
    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDate getStart_date() {
        return start_date;
    }
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getEnd_date() {
        return end_date;
    }
    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
    public String getEmployee_requester() {
        return employee_requester;
    }
    public void setEmployee_requester(String employee_requester) {
        this.employee_requester = employee_requester;
    }
    public String getEmployee_approver() {
        return employee_approver;
    }
    public void setEmployee_approver(String employee_approver) {
        this.employee_approver = employee_approver;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    

    
}
