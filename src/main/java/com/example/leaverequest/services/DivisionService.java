package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Division;

public interface DivisionService {
    public List<Division> getAll();
    public Division getById(Integer id);
    public Boolean save(Division division);
    public Boolean delete(Integer id);
    
}
