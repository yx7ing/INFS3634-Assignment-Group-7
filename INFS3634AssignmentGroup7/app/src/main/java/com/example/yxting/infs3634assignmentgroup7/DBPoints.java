package com.example.yxting.infs3634assignmentgroup7;

// Only requires one row, stores the players points (and medals for every 1000 points)
public class DBPoints {
    int id;
    int points;

    public DBPoints(int id, int points) {
        this.id = id;
        this.points = points;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
