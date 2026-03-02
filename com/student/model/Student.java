package com.student.model;

public class Student {
    private int id;
    private String name;
    private int progress;

    // Constructor
    public Student(int id, String name, int progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
    }

    // Getters and Setters (needed so Java can read/write the data)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
}