package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Leave;

public interface LeaveService {
    public List<Leave> getALL();
    public Leave getById(Integer id);
    public Boolean save(Leave leave);
    public Boolean delete (Integer id);
}
