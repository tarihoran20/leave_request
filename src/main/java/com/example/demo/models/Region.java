package com.example.demo.models;

import javax.persistence.*;



@Entity
@Table(name = "tb_m_regions")
public class Region {
    @Id
    @Column(name = "regionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;

    
    @Column(name = "regionName", nullable = false)
    private String regionName;


    public void setRegionId(int regionId){
        this.regionId = regionId;
    }

    public int getRegionId(){
        return regionId;
    }

    public void setRegionName(String regionName){
        this.regionName = regionName;
    }

    public String getRegionName(){
        return regionName;
    }

    // @OneToMany(mappedBy = "region")
    // Set<RegionDivision> regdiv;
}
