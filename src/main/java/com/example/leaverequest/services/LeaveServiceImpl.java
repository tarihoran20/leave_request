package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.models.Leave;
import com.example.leaverequest.repositories.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public List<Leave> getALL() {
        
        return leaveRepository.findAll();
    }

    @Override
    public Leave getById(Integer id) {
        return leaveRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Leave type not found"));
    }

    @Override
    public Boolean save(Leave leave) {
        leaveRepository.save(leave);
        return leaveRepository.findById(leave.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        leaveRepository.deleteById(id);
        return !leaveRepository.findById(id).isPresent();
    }
    
}
