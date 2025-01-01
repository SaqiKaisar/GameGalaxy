package com.example.gamegalaxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class CreateDatabase {
    public void createDatabase() {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            // Open a connection
            System.out.println("Connecting to database...");
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost/", "postgres", "postgres");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Execute a query to create database
            System.out.println("Creating database...");
            try {
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String sql = "CREATE DATABASE GameGalaxy";
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            //System.out.println("Database created successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
