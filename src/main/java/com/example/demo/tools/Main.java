package com.example.demo.tools;

import com.example.demo.daos.RegionDAO;
import com.example.demo.daos.DivisionDAO;
import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class Main {

    public static void main(String[] args) {
        getAllRegion();
        getAllDivision();
    }

    public static void getAllRegion(){
        DbConnection connection = new DbConnection();
        //System.out.println(connection.getConnection());
        RegionDAO rdao = new RegionDAO(connection.getConnection());
        for (Region region : rdao.getAll()) {
            System.out.print(region.getRegionId() + " ");
            System.out.println(region.getRegionName());
            
        }
    }

    public static void getAllDivision(){
        DbConnection connection = new DbConnection();
        //System.out.println(connection.getConnection());
        DivisionDAO ddao = new DivisionDAO(connection.getConnection());
        for (Division division : ddao.getAll()) {
            System.out.print(division.getDivisionId() + " ");
            System.out.println(division.getDivisionName());
            
        }
    }
    
}
