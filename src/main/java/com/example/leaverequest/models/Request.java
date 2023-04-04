package com.example.leaverequest.models;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "start_date", nullable = false)    
    private LocalDate start_date;

    // @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "end_date", nullable = false)    
    private LocalDate end_date;

    @Column(name = "temp_address", nullable = false)    
    private String temp_address;

    @Column(name = "temp_phone_number", nullable = false)    
    private String temp_phone_number;

    @Column(name = "description", nullable = false)    
    private String description;

    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private Leave leave;

    @ManyToOne
    @JoinColumn(name = "employee_backup_id", referencedColumnName = "id")
    private Employee employee;

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

    public String getTemp_address() {
        return temp_address;
    }

    public void setTemp_address(String temp_address) {
        this.temp_address = temp_address;
    }

    public String getTemp_phone_number() {
        return temp_phone_number;
    }

    public void setTemp_phone_number(String temp_phone_number) {
        this.temp_phone_number = temp_phone_number;
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    
    
}
