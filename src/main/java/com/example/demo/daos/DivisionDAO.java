package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class DivisionDAO {
    private Connection connection;

    public DivisionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT d.divisionId, d.divisionName, r.regionName FROM tb_m_divisions d JOIN tb_m_regions r on d.regionId = r.regionId";
        try{
            ResultSet resultSet = connection
                        .prepareStatement(query)
                        .executeQuery();

            while (resultSet.next()) {
                Division division = new Division();
                division.setDivisionId(resultSet.getInt(1));
                division.setDivisionName(resultSet.getString(2));
                division.setRegionName(resultSet.getString(3));
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;

    }

    public boolean insert(Division division){
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DIVISIONS (region_id, region_name) VALUES(?, ?)");
            preparedStatement.setInt(1, division.getDivisionId());
            preparedStatement.setString(2, division.getDivisionName());
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean delete(Division division) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DIVISIONS WHERE division_id = ?");
            preparedStatement.setInt(1, division.getDivisionId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DIVISIONS SET division_name = ? WHERE division_id = ? ");
            preparedStatement.setString(1, division.getDivisionName());
            preparedStatement.setInt(2, division.getDivisionId());
            int temp = preparedStatement.executeUpdate();

            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Division getById(Integer divisionId) {
        Division region = new Division();
        String query = "SELECT * FROM tb_m_divisions WHERE divisionId = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, divisionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                region.setDivisionId(resultSet.getInt(1));
                region.setDivisionName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }
    
}
