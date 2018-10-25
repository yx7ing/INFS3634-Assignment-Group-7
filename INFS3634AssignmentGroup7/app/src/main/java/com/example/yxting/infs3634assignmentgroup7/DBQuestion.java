package com.example.yxting.infs3634assignmentgroup7;

// Database table for storing questions, the 4 multiple choice answers, the correct answer, and the related topic
public class DBQuestion {
    int id;
    String question;
    String answera;
    String answerb;
    String answerc;
    String answerd;
    int correctans;
    String topic;

    public DBQuestion(int id, String question, String answera, String answerb, String answerc, String answerd, int correctans, String topic) {
        this.id = id;
        this.question = question;
        this.answera = answera;
        this.answerb = answerb;
        this.answerc = answerc;
        this.answerd = answerd;
        this.correctans = correctans;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswera() {
        return answera;
    }

    public void setAnswera(String answera) {
        this.answera = answera;
    }

    public String getAnswerb() {
        return answerb;
    }

    public void setAnswerb(String answerb) {
        this.answerb = answerb;
    }

    public String getAnswerc() {
        return answerc;
    }

    public void setAnswerc(String answerc) {
        this.answerc = answerc;
    }

    public String getAnswerd() {
        return answerd;
    }

    public void setAnswerd(String answerd) {
        this.answerd = answerd;
    }

    public int getCorrectans() {
        return correctans;
    }

    public void setCorrectans(int correctans) {
        this.correctans = correctans;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
