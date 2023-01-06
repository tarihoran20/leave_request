package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.demo.models.Region;
import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionDAO {
    private Connection connection;

    public RegionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_regions";
        try{
            ResultSet resultSet = connection
                        .prepareStatement(query)
                        .executeQuery();

            while (resultSet.next()) {
                Region region = new Region();
                region.setRegionId(resultSet.getInt(1));
                region.setRegionName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;

    }

    public Boolean insert (Region region){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_regions (regionName) VALUES(?)");
            //preparedStatement.setInt(1, region.getRegionId());
            preparedStatement.setString(1, region.getRegionName());
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            //return temp > 0;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(Integer regionId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_m_regions WHERE regionId = ?");
            preparedStatement.setInt(1, regionId);
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Region region) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_regions SET regionName = ? WHERE regionId = ? ");
            preparedStatement.setString(1, region.getRegionName());
            preparedStatement.setInt(2, region.getRegionId());
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // public Region getById(int regionId) {
    //     Region region = new Region();
    //     String query = "SELECT * FROM tb_m_regions WHERE regionId = " + regionId;
    //     try{
    //         ResultSet resultSet = connection
    //                     .prepareStatement(query)
    //                     .executeQuery();

    //         while (resultSet.next()) {
    //             region.setRegionId(resultSet.getInt(1));
    //             region.setRegionName(resultSet.getString(2));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return region;
    // }
    public Region getById(Integer regionId) {
        Region region = new Region();
        String query = "SELECT * FROM tb_m_regions WHERE regionId = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, regionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                region.setRegionId(resultSet.getInt(1));
                region.setRegionName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

}
