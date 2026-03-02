package com.student.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    // This URL points to the database we will create in phpMyAdmin
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root";
    private static final String PASS = ""; // XAMPP default password is empty

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // This loads the MySQL driver you added to your Referenced Libraries
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
