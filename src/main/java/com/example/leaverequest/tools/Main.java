package com.example.leaverequest.tools;

import com.example.leaverequest.daos.DivisionDAO;
import com.example.leaverequest.daos.RegionDAO;
import com.example.leaverequest.models.Division;
import com.example.leaverequest.models.Region;

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
            System.out.print(region.getId() + " ");
            System.out.println(region.getName());
            
        }
    }

    public static void getAllDivision(){
        DbConnection connection = new DbConnection();
        //System.out.println(connection.getConnection());
        DivisionDAO ddao = new DivisionDAO(connection.getConnection());
        for (Division division : ddao.getAll()) {
            System.out.print(division.getId() + " ");
            System.out.println(division.getName());
            
        }
    }
    
}
