package com.example.leaverequest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.example.leaverequest.dto.ApprovalDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;
import com.example.leaverequest.repositories.EmployeeRepository;
import com.example.leaverequest.repositories.RequestRepository;
import com.example.leaverequest.repositories.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

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
    public Boolean save2(Status status) {
        statusRepository.save(status);
        return statusRepository.findById(status.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        statusRepository.deleteById(id);
        return !statusRepository.findById(id).isPresent();
    }

    @Override
    public List<Object> getBackupRequestedByEmail(String email) {
        // List<Employee> employee = employeeCourseRepository.findCourseByEmployeeIdCheck(id);
        // return status. 
        // statusRepository.stream().map(it->convertToDto(it)).collect(Collectors.toList());
        //return employeeCourseRepository.findCourseByEmployeeIdDTO(id);

        return statusRepository.getBackupRequestedByEmail(email);
    }

    @Override
    public List<Object> getBackupRejectApprovedByEmail(Integer employee_id) {
        return statusRepository.getBackUpRejectApprovedByEmail(employee_id);
    }

    @Override
    public Integer getStatusIdByRequestId(Integer request_id) {
        return statusRepository.getStatusIdByRequestId(request_id);
    }

    @Override
    public Integer getEmployeeIdApproverByStatusId(Integer status_id) {
        return statusRepository.getEmployeeIdApproverByStatusId(status_id);
    }

    @Override
    public Integer getEmployeeIdRequesterByStatusId(Integer status_id){
        return statusRepository.getEmployeeIdRequesterByStatusId(status_id);
    }

    @Override
    public List<Object> getAll(String email) {
        return statusRepository.getAll(email);
    }

    @Override
    public List<Integer> getAllRequestIdByEmployeeId(Integer employee_id){
        return statusRepository.getAllRequestIdByEmployeeId(employee_id);
 
    }

    @Override
    public List<Request> getAllRequestByRequestIdAndEmployeeId(Integer request_id, Integer employee_id) {
        return requestRepository.getAllRequestByRequestIdAndEmployeeId(request_id, employee_id);
    }

    @Override
    public List<Object> getAllRejectedApprovedByRequestIdAndEmployeeId(Integer request_id, Integer employee_id) {
        return statusRepository.getAllRejectedApprovedByRequestIdAndEmployeeId(request_id, employee_id);
    }

    @Override
    public List<Request> getRequestHistoryByEmployeeId(Integer employee_request_id) {
        return requestRepository.getRequestHistoryByEmployeeId(employee_request_id);
    }

    @Override
    public Status getStatusByRequestId(Integer request_id) {
        return statusRepository.findStatusByRequestId(request_id);
    }

    @Override
    public List<Request> getRequestHistoryByEmployeeIdTest() {
        return requestRepository.getAllRequestByRequestIdAndEmployeeIdTest();
    }

    @Override
    public Status getStatus() {
        return statusRepository.findStatus();
    }

    @Override
    public List<Status> getAllStatusByEmployeeId(Integer employee_request_id) {
       
        return statusRepository.getAllStatusByEmployeeId(employee_request_id);
    }

    @Override
    public Request getDataRequestByRequestId(Integer request_id) {
        return requestRepository.getDataRequestByRequestId(request_id);
    }

    

    


    


    

    
}
