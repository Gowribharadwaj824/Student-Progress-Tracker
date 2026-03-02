package com.student.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import com.student.util.DBConnection;
import com.student.dao.StudentDAOo;

public class ViewStudents extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewStudents() {
        // 1. Basic Window Setup
        setTitle("Student Management System - All Records");
        setSize(700, 500);
        setLayout(new BorderLayout());

        // 2. Setup the Table (The "Read" part of CRUD)
        String[] columnNames = {"ID", "Student Name", "Progress (%)"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. Create the Control Panel for Buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnDelete = new JButton("Delete by ID");
        JButton btnUpdate = new JButton("Update Progress");
        JButton btnRefresh = new JButton("Refresh Table");

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- BUTTON ACTIONS ---

        // Refresh Table Action
        btnRefresh.addActionListener(e -> loadData());

        // Delete Student Action
        btnDelete.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Enter Student ID to Delete:");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    new StudentDAOo().deleteStudent(id);
                    JOptionPane.showMessageDialog(this, "Record Deleted!");
                    loadData(); // Refresh table automatically
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
                }
            }
        });

        // Update Progress Action
        btnUpdate.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Enter Student ID:");
            String progStr = JOptionPane.showInputDialog(this, "Enter New Progress (%):");
            if (idStr != null && progStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    int newProg = Integer.parseInt(progStr);
                    new StudentDAOo().updateStudent(id, newProg);
                    JOptionPane.showMessageDialog(this, "Progress Updated!");
                    loadData(); // Refresh table automatically
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
                }
            }
        });

        // Load data when window opens
        loadData();

        setLocationRelativeTo(null); // Center window on screen
        setVisible(true);
    }

    // Method to fetch data from MySQL and show in Table
    private void loadData() {
        model.setRowCount(0); // Clear table first
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("progress") + "%"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewStudents();
    }
}