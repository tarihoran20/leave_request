package com.example.leaverequest.dto;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.leaverequest.models.Division;

public class RegisterDTO {
    private Integer id;
    private String nik;
    private String fullname;
    private String email;
    private String phone_number;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate join_date;
    private Integer remaining_leave;
    // private Division division;

    @ManyToOne
    @JoinColumn(name = "id")
    private Division division;

    private String Password;
    //private Integer role_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoin_date() {
        return join_date;
    }

    public void setJoin_date(LocalDate join_date) {
        this.join_date = join_date;
    }

    public Integer getRemaining_leave() {
        return remaining_leave;
    }

    public void setRemaining_leave(Integer remaining_leave) {
        this.remaining_leave = remaining_leave;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    
    
    

    
}
