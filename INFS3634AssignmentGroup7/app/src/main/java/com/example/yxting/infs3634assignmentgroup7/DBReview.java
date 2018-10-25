package com.example.yxting.infs3634assignmentgroup7;

// Database table for storing the user's most recent answer to each quiz question
// and whether they have answered that question correctly at least once before
// The purpose of this is that the user should only earn points for the first time
// they answer a question correctly
public class DBReview {
    int id;
    int recentans;
    int correctonce; // Boolean, 0 for false, 1 for true

    public DBReview(int id, int recentans, int correctonce) {
        this.id = id;
        this.recentans = recentans;
        this.correctonce = correctonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecentans() {
        return recentans;
    }

    public void setRecentans(int recentans) {
        this.recentans = recentans;
    }

    public int getCorrectonce() {
        return correctonce;
    }

    public void setCorrectonce(int correctonce) {
        this.correctonce = correctonce;
    }
}
