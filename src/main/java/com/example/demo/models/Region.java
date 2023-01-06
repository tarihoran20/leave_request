package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "tb_m_regions")
public class Region {
    @Id
    @Column(name = "regionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RegionId;

    @Column(name = "regionName", nullable = false)
    private String RegionName;

    public void setRegionId(Integer regionId){
        RegionId = regionId;
    }

    public Integer getRegionId(){
        return RegionId;
    }

    public void setRegionName(String regionName){
        RegionName = regionName;
    }

    public String getRegionName(){
        return RegionName;
    }

    // @OneToMany(mappedBy = "region")
    // Set<RegionDivision> regdiv;
}
