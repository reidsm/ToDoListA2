package com.example.todolista2;

import java.util.Date;

public class ToDo {
    private String taskName;
    private String person;
    private String date;
    private Boolean done;

    public ToDo() {}

    public ToDo(String taskName, String person, String date, Boolean done) {
        this.taskName = taskName;
        this.person = person;
        this.date = date;
        this.done = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String result = this.taskName + " " + this.person + " " + this.done + " " + this.date;
        return result;
    }
}
