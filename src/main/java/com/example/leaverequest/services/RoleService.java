package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.models.Role;

public interface RoleService {
    public List<Role> getAll();
    public Role getById(Integer id);
    public Boolean save(Role role);
    public Boolean delete (Integer id);
    public int getLevelById();
    
}
