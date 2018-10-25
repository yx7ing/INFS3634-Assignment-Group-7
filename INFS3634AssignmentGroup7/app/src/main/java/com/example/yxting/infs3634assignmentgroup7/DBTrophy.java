package com.example.yxting.infs3634assignmentgroup7;

// Database table for tracking which trophies have been earned
public class DBTrophy {
    int id;
    int earned; // This is a boolean, but for SQLite it will be 0 or 1

    public DBTrophy(int id, int earned){
        this.id = id;
        this.earned = earned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }
}