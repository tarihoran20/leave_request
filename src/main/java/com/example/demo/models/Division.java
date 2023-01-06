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
    private Integer DivisionId;

    @Column(name = "divisionName", nullable = false)    
    private String DivisionName;

    @Column(name = "regionName", nullable = false)
    private String RegionName;

    public Integer getDivisionId() {
        return DivisionId;
    }
    public void setDivisionId(Integer divisionId) {
        DivisionId = divisionId;
    }
    public String getDivisionName() {
        return DivisionName;
    }
    public void setDivisionName(String divisionName) {
        DivisionName = divisionName;
    }
    public String getRegionName(){
        return RegionName;
    }
    public void setRegionName(String regionName){
        RegionName = regionName;
    }
    
    // @OneToMany(mappedBy = "division")
    // Set<RegionDivision> regdiv;
    
}
