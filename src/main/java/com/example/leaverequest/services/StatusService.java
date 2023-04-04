package com.example.leaverequest.services;

import java.util.List;

import com.example.leaverequest.dto.ApprovalDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;

public interface StatusService {

    public List<Status> getAll();
    public Status getById(Integer id);
    public Boolean save(Status status);
    public Boolean save2(Status status);
    public Boolean delete (Integer id);
    public List<Object> getBackupRequestedByEmail(String email);
    public Integer getStatusIdByRequestId(Integer request_id);
    public Integer getEmployeeIdApproverByStatusId(Integer status_id);
    public Integer getEmployeeIdRequesterByStatusId(Integer status_id);
    public List<Object> getBackupRejectApprovedByEmail(Integer employee_id) ;
    public List<Object> getAll(String email) ;
    public List<Integer> getAllRequestIdByEmployeeId(Integer employee_id);
    public List<Request> getAllRequestByRequestIdAndEmployeeId(Integer request_id, Integer employee_id);
    public List<Object> getAllRejectedApprovedByRequestIdAndEmployeeId(Integer request_id, Integer employee_id);
    public List<Request> getRequestHistoryByEmployeeId(Integer employee_request_id);
    Status getStatusByRequestId(Integer request_id);
    Status getStatus();
    public List<Request> getRequestHistoryByEmployeeIdTest();
    public List<Status> getAllStatusByEmployeeId(Integer employee_request_id);
    Request getDataRequestByRequestId(Integer request_id);
    
}
