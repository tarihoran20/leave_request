package com.example.leaverequest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.example.leaverequest.dto.LoginDTO;
import com.example.leaverequest.models.Employee;
import com.example.leaverequest.models.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select e.id as id, e.fullname as fullname, e.email as email from employee e " +
            "join user u on e.id = u.id join role r on r.id = u.role_id  WHERE e.email = ?1 && u.password = ?2", nativeQuery = true)
    Employee findEmailAndPassword(String email, String password);

    @Query(value = "select * from employee e join user on e.id = u.id where e.email = ?1", nativeQuery = true)
    Employee findByEmail(String email);

    @Query(value = "SELECT e.id FROM employee e WHERE e.email = ?1", nativeQuery = true)
    public Integer findIdByEmail(String Email);

    @Query(value = "select e.email from employee e where e.email = ?1", nativeQuery = true)
    public Employee findEmail(String email);

    @Query(value = "select * from user u join employee e on u.id = e.id join role r on u.role_id = r.id where e.email = ?1", nativeQuery = true)
    public User loginUser(String email);

    @Query(value = "select * from employee e join user u on e.id = u.id join role r on u.role_id = r.id where e.email = ?1", nativeQuery = true)
    public Employee loginEmployee(String email);

    @Query(value = "SELECT id FROM employee WHERE email = ?", nativeQuery = true)
    Integer getIdByEmail(String email);

    @Query(value = "SELECT id FROM employee WHERE email = ?", nativeQuery = true)
    Employee findByUsername(String name);

    @Query(value = "select d.manager_id from employee e join division d on e.division_id = d.id where email = ?1", nativeQuery = true)
    Integer getManagerIdByEmail(String name);

    @Query(value = "select e.id from employee e join user u on e.id = u.id join role r on u.role_id = r.id where r.level = 2", nativeQuery = true)
    Integer getEmployeeHrId();

    @Query(value = "SELECT fullname FROM employee WHERE id = ?1", nativeQuery = true)
    String getNameById(Integer id);

    @Query(value = "select e.* from employee e where id = ?1", nativeQuery = true)
    Employee getDataEmployeeByEmployeeId(Integer request_id);

    @Query(value = "SELECT e.* " +
            "FROM employee e " +
            "WHERE e.division_id = (SELECT e.division_id " +
            "FROM employee e " +
            "WHERE e.id = ?1) " +
            "AND NOT e.id = ?2", nativeQuery = true)
    public List<Employee> getEmployeeBackupList(Integer employee_request_id, Integer employee_request_id2);

}
