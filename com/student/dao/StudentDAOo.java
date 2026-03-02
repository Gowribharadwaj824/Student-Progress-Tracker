package com.student.dao;

import java.sql.*;
import com.student.util.DBConnection;

public class StudentDAOo {

    // 1. Method to ADD a new student
    public void addStudent(String name, int progress) {
        String sql = "INSERT INTO students (name, progress) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, progress);
            pstmt.executeUpdate();
            System.out.println("Student added to database!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2. Method to DELETE a student by ID
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Student deleted successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3. Method to UPDATE a student's progress by ID
    public void updateStudent(int id, int newProgress) {
        String sql = "UPDATE students SET progress = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, newProgress);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Student progress updated!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}