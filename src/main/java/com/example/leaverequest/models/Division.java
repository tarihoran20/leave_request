package com.example.leaverequest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "division")
public class Division {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)    
    private String name;

    @Column(name = "manager_id", nullable = false)    
    private Integer manager_id;

    // @ManyToOne
    // @JoinColumn(name = "manager")
    // private Employee employee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    

    // public Employee getEmployee() {
    //     return employee;
    // }

    // public void setEmployee(Employee employee) {
    //     this.employee = employee;
    // }

    
    
}
