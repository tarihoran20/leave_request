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

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO REGIONS (region_id, region_name) VALUES(?, ?)");
            preparedStatement.setInt(1, region.getRegionId());
            preparedStatement.setString(2, region.getRegionName());
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            //return temp > 0;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(Region region) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM REGIONS WHERE region_id = ?");
            preparedStatement.setInt(1, region.getRegionId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE REGIONS SET region_name = ? WHERE region_id = ? ");
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
}
