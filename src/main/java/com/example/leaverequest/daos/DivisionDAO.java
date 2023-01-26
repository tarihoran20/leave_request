package com.example.leaverequest.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.example.leaverequest.models.Division;
import com.example.leaverequest.models.Region;

public class DivisionDAO {
    private Connection connection;

    public DivisionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();

        String query = "SELECT d.id, d.name, r.name FROM tb_m_divisions d JOIN tb_m_regions r on d.regionId = r.id ORDER BY d.id ASC" ;
        try{
            ResultSet resultSet = connection
                        .prepareStatement(query)
                        .executeQuery();           

            while (resultSet.next()) {
                Division division = new Division();
                Region region = new Region();
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                region.setName(resultSet.getString(3));
                // division.setRegion(region);

                //division.setRegionName(resultSet.getString(3));
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;

    }

    public boolean insert(Division division){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tb_m_divisions (name, regionId) VALUES(?, ?)");
            //Region region = new Region();
            preparedStatement.setString(1, division.getName());

            // preparedStatement.setObject(2, division.getRegion().getId());
            
            int temp = preparedStatement.executeUpdate();

            //return temp > 0;
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tb_m_divisions WHERE id = ?");
            preparedStatement.setInt(1, id);
            //preparedStatement.execute();
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Division division) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tb_m_divisions SET name = ?, regionId = ? WHERE id = ? ");

            preparedStatement.setString(1, division.getName());
            // preparedStatement.setInt(2, division.getRegion().getId());
            
            preparedStatement.setInt(3, division.getId());
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Division getById(Integer id) {
        Division division = new Division();
        String query = "SELECT * FROM tb_m_divisions WHERE Id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }
    
    public List<Region> getAllRegion(){
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

                //division.setRegionName(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;

    }

}
