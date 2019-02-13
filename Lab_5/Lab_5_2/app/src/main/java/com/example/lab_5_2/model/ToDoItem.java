package com.example.lab_5_2.model;

public class ToDoItem {

    String name = null;
    String description = null;
    String date = null;
    int checked = 0;

    public ToDoItem(String name, String description, String date, int checked) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

}
