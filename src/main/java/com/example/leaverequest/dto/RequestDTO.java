package com.example.leaverequest.dto;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Leave;

public class RequestDTO {

    private Integer id;
    private Integer leave_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private String temp_phone_number;
    private String temp_address;
    private String description;
    private Integer employee_backup_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "status", nullable = false)    
    private String status;

    private Integer employee_request_id;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    
    public String getTemp_phone_number() {
        return temp_phone_number;
    }
    public void setTemp_phone_number(String temp_phone_number) {
        this.temp_phone_number = temp_phone_number;
    }
    public String getTemp_address() {
        return temp_address;
    }
    public void setTemp_address(String temp_address) {
        this.temp_address = temp_address;
    }
    
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getLeave_id() {
        return leave_id;
    }
    public void setLeave_id(Integer leave_id) {
        this.leave_id = leave_id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmployee_backup_id() {
        return employee_backup_id;
    }
    public void setEmployee_backup_id(Integer employee_backup_id) {
        this.employee_backup_id = employee_backup_id;
    }
    public Integer getEmployee_request_id() {
        return employee_request_id;
    }
    public void setEmployee_request_id(Integer employee_request_id) {
        this.employee_request_id = employee_request_id;
    }

    
    
}
