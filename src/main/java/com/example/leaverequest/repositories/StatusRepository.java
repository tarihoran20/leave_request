package com.example.leaverequest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.leaverequest.dto.ApprovalDTO;
import com.example.leaverequest.models.Request;
import com.example.leaverequest.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

    @Query(value = "select s.id as 'Status Id', r.id as 'Request Id', s.date, r.start_date, r.end_date, e2.fullname as 'R', e1.fullname as 'A', s.status from status s join request r on s.request_id = r.id join leavetyp l on l.id = r.leave_id join employee e1 on e1.id = s.employee_id join employee e2 on e2.id = s.employee_request_id where e1.email = ?1 order by s.id desc", nativeQuery = true)
    List<Object> getAll(String email);
    
    @Query(value = "select s.id as 'Status Id', r.id as 'Request Id', s.date, r.start_date, r.end_date, e2.fullname as 'R', e1.fullname as 'A', s.status from status s join request r on s.request_id = r.id join leavetyp l on l.id = r.leave_id join employee e1 on e1.id = s.employee_id join employee e2 on e2.id = s.employee_request_id where s.status = 'Requested ..' AND e1.email = ?1", nativeQuery = true)
    List<Object> getBackupRequestedByEmail(String email);

    @Query(value = "select s.id as 'Status Id', r.id as 'Request Id', s.date, r.start_date, r.end_date, e2.fullname as 'R', e1.fullname as 'A', s.status from status s join request r on s.request_id = r.id join leavetyp l on l.id = r.leave_id join employee e1 on e1.id = s.employee_id join employee e2 on e2.id = s.employee_request_id where s.employee_id = ?1 AND (s.status = 'Rejected' OR s.status = 'Approved')", nativeQuery = true)
    List<Object> getBackUpRejectApprovedByEmail(Integer employee_id);

    @Query(value = "select s.id from status s where s.request_id = ?1 limit 1", nativeQuery = true)
    Integer getStatusIdByRequestId(Integer request_id);

    @Query(value = "select s.employee_id from status s where s.id = ?1", nativeQuery = true)
    Integer getEmployeeIdApproverByStatusId(Integer status_id);

    @Query(value = "select s.employee_request_id from status s where s.id = ?1", nativeQuery = true)
    Integer getEmployeeIdRequesterByStatusId(Integer status_id);

    @Query(value = "select s.request_id from status s join employee e on s.employee_id = e.id where s.employee_id = ?1 group by request_id;", nativeQuery = true)
    List<Integer> getAllRequestIdByEmployeeId(Integer employee_id);

    @Query(value = "select * from status where request_id = ?1 and employee_id = ?2 and (status = 'Rejected' or status = 'Approved') ", nativeQuery = true)
    List<Object> getAllRejectedApprovedByRequestIdAndEmployeeId(Integer request_id, Integer employee_id);

    @Query(value = "SELECT * FROM status s WHERE s.id = (SELECT MAX(s.id) FROM status s where s.request_id = ?1)", nativeQuery = true)
    Status findStatusByRequestId(Integer request_id);

    @Query(value = "SELECT * FROM status s ", nativeQuery = true)
    Status findStatus();

    @Query(value = "select s.* from status s join request r on s.request_id = r.id join employee e1 on s.employee_id = e1.id join employee e2 on s.employee_request_id = e2.id join leavetyp l on r.leave_id = l.id where employee_request_id = ?1 order by s.request_id DESC", nativeQuery = true)
    List<Status> getAllStatusByEmployeeId(Integer employee_request_id);

    
    
    
}
