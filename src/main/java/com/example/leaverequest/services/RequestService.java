package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Request;

public interface RequestService {
    public List<Request> getALL();
    public Request getById(Integer id);
    public Boolean save(Request request);
    public Boolean delete (Integer id);
    public Integer getLeaveByEmail(String email);
}
