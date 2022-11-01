package com.dmt.dangtus.learnandroid.model;

public class Computer {
    private int id;
    Category category;
    private String name;

    public Computer() {
    }

    public Computer(int id, Category category, String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
