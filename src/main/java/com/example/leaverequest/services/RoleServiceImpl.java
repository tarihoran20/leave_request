package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leaverequest.models.Role;
import com.example.leaverequest.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Role tidak ditemukan"));
    }

    @Override
    public Boolean save(Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        roleRepository.deleteById(id);
        return !roleRepository.findById(id).isPresent();
    }

    @Override
    public int getLevelById() {
        return roleRepository.getMaxLevelById();
    }

    @Override
    public String getRoleNameByEmployeeId(Integer id){
        return roleRepository.getRoleNameByEmployeeId(id);
    }
    
}
