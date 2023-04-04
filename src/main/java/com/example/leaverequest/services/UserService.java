package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.dto.RegisterDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;

public interface UserService {
    public List<User> getAll();
    public Boolean save(User user);
    // public Object login(LoginDTO loginDTO);
    public User getById(Integer id);
    // public User findUserByPassword(String password);
    public void updatePasswordEmployee(Integer id, String Password);
    List<User> getAllDataEmployeeById(Integer employee_id);
    User getAllDataEmployeeById2(Integer employee_id);
    public Employee getDataEmployeeByEmployeeId(Integer employee_id);
}
