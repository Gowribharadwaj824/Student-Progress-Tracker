package com.student.view;

import javax.swing.*;
import java.awt.*;
import com.student.dao.StudentDAOo;

public class StudentForm extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtProgress = new JTextField(10);
    private JButton btnSave = new JButton("Save Progress");

    public StudentForm() {
        setTitle("Student Progress Tracker");
        setLayout(new GridLayout(3, 2, 10, 10));
        
        add(new JLabel("Student Name:")); add(txtName);
        add(new JLabel("Progress (%):")); add(txtProgress);
        add(new JLabel("")); add(btnSave);

        btnSave.addActionListener(e -> {
            String name = txtName.getText();
            int progress = Integer.parseInt(txtProgress.getText());
            new StudentDAOo().addStudent(name, progress); // Calls our DAO
            JOptionPane.showMessageDialog(this, "Data Saved to MySQL!");
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}