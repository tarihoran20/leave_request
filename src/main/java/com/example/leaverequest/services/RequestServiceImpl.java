package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.models.Request;
import com.example.leaverequest.repositories.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getALL() {
        return requestRepository.findAll();
    }

    @Override
    public Request getById(Integer id) {
        return requestRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Request type not found"));
    }

    @Override
    public Boolean save(Request request) {
        requestRepository.save(request);
        return requestRepository.findById(request.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        requestRepository.deleteById(id);
        return !requestRepository.findById(id).isPresent();
    }

    @Override
    public Integer getLeaveByEmail(String email) {
        return requestRepository.getLeaveByEmail(email);
    }
    
}
