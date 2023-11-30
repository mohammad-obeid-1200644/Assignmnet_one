package com.example.assignmnet_one;

public class TheTask {
    String Name,Desc,date;
    boolean done;

    public TheTask() {
    }

    public TheTask(String name, String desc, String date,boolean done) {
        Name = name;
        Desc = desc;
        this.date = date;
        this.done=done;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
