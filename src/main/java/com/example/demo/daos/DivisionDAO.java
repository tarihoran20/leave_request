package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.example.demo.models.Division;

public class DivisionDAO {
    private Connection connection;

    public DivisionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT * FROM tb_m_divisions";
        try{
            ResultSet resultSet = connection
                        .prepareStatement(query)
                        .executeQuery();

            while (resultSet.next()) {
                Division division = new Division();
                division.setDivisionId(resultSet.getInt(1));
                division.setDivisionName(resultSet.getString(2));
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
    
}
