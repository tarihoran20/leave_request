package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.models.Status;
import com.example.leaverequest.repositories.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status getById(Integer id) {
        return statusRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Role tidak ditemukan"));
    }

    @Override
    public Boolean save(Status status) {
        statusRepository.save(status);
        return statusRepository.findById(status.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        statusRepository.deleteById(id);
        return !statusRepository.findById(id).isPresent();
    }
    
}
