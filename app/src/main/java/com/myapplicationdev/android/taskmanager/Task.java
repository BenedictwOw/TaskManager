package com.myapplicationdev.android.taskmanager;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String tName;
    private String tDes;

    public Task(int id, String tName, String tDes) {
        this.id = id;
        this.tName = tName;
        this.tDes = tDes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDes() {
        return tDes;
    }

    public void settDes(String tDes) {
        this.tDes = tDes;
    }

}
