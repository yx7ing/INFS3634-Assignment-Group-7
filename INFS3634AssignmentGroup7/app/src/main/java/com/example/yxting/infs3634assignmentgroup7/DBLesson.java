package com.example.yxting.infs3634assignmentgroup7;

// Database table for tracking which lessons have been viewed
public class DBLesson {
    int id;
    int viewed; // This is a boolean, but for SQLite it will be 0 or 1

    public DBLesson(int id, int viewed){
        this.id = id;
        this.viewed = viewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }
}
