package com.example.leaverequest.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import com.example.leaverequest.models.Region;

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
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;

    }

    public Boolean insert (Region region){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_regions (name) VALUES(?)");
            //preparedStatement.setInt(1, region.getId());
            preparedStatement.setString(1, region.getName());
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            //return temp > 0;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(Integer Id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_m_regions WHERE id = ?");
            preparedStatement.setInt(1, Id);
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_regions SET name = ? WHERE id = ? ");
            preparedStatement.setString(1, region.getName());
            preparedStatement.setInt(2, region.getId());
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
    public Region getById(Integer Id) {
        Region region = new Region();
        String query = "SELECT * FROM tb_m_regions WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                region.setId(resultSet.getInt(1));
                region.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

}
