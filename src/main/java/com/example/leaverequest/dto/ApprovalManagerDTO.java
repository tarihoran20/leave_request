package com.example.leaverequest.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ApprovalManagerDTO {
    private Integer id;
    private String leave;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;
    private String employee_backup_id;
}
