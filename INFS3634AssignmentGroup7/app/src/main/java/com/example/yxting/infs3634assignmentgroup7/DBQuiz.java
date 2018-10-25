package com.example.yxting.infs3634assignmentgroup7;

// Database table for tracking which quizzes have been attempted, and the highest mark earned
public class DBQuiz {
    int id;
    int completed;
    int highest;

    public DBQuiz(int id, int completed, int highest) {
        this.id = id;
        this.completed = completed;
        this.highest = highest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }
}
