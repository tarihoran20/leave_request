package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Status;

public interface StatusService {

    public List<Status> getAll();
    public Status getById(Integer id);
    public Boolean save(Status status);
    public Boolean delete (Integer id);
    
}
