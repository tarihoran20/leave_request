package com.example.leaverequest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.leaverequest.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "select e.remaining_leave from employee e where e.email = ?1", nativeQuery = true)
    public Integer getLeaveByEmail(String email);

    @Query(value = "select r.* from request as r join status as s on r.id=s.request_id join employee as e on s.employee_id=e.id join user as u on e.id=u.id join role as rl on u.role_id=rl.id where s.id = (select max(s.id) from status s join request r on r.id=s.request_id where s.employee_request_id= ?)", nativeQuery = true)
    List<Request> getRequestHistoryByEmployeeId(Integer employee_request_id);

    @Query(value = "select r.* from status s join request r on s.request_id = r.id join employee e1 on s.employee_id = e1.id join employee e2 on s.employee_request_id = e2.id join leavetyp l on r.leave_id = l.id where request_id = ?1 and employee_id = ?2 and status = 'Requested ..' ", nativeQuery = true)
    List<Request> getAllRequestByRequestIdAndEmployeeId(Integer request_id, Integer employee_id);

    @Query(value = "select r.* from status s join request r on s.request_id = r.id join employee e1 on s.employee_id = e1.id join employee e2 on s.employee_request_id = e2.id join leavetyp l on r.leave_id = l.id order by r.id desc ", nativeQuery = true)
    List<Request> getAllRequestByRequestIdAndEmployeeIdTest();

    @Query(value = "select r.* from status s join request r on s.request_id = r.id join employee e1 on s.employee_id = e1.id join employee e2 on s.employee_request_id = e2.id join leavetyp l on r.leave_id = l.id order by r.id desc ", nativeQuery = true)
    List<Request> getAllRequestByRequestIdAndEmployeeIdTest2();

    @Query(value = "select r.* from request r where id = ?1", nativeQuery = true)
    Request getDataRequestByRequestId(Integer request_id);
    
}
