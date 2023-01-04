package com.example.demo.tools;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static Connection con;

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/companyspring", "root", "1234");
        } catch (Exception e) {
            System.out.println("Error + " + e.getMessage());
        }
        return con;
    }
    
}
