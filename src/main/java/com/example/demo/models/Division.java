package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "tb_m_divisions")
public class Division {

    @Id
    @Column(name = "divisionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer divisionId;
    @Column(name = "regionName", nullable = false)    
    private String divisionName;

    public Integer getDivisionId() {
        return divisionId;
    }
    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }
    public String getDivisionName() {
        return divisionName;
    }
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    
    // @OneToMany(mappedBy = "division")
    // Set<RegionDivision> regdiv;
    
}
