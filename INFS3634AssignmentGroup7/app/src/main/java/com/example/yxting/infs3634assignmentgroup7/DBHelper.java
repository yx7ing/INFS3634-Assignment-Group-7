package com.example.yxting.infs3634assignmentgroup7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "Database";

    // Table creation
    private static final String CREATE_TABLE_LESSON = "CREATE TABLE Lesson(id INTEGER PRIMARY KEY, viewed INTEGER)";
    private static final String CREATE_TABLE_TROPHY = "CREATE TABLE Trophy(id INTEGER PRIMARY KEY, earned INTEGER)";
    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE Question(id INTEGER PRIMARY KEY, " +
                                                                              "question TEXT, " +
                                                                              "answera TEXT, " +
                                                                              "answerb TEXT, " +
                                                                              "answerc TEXT, " +
                                                                              "answerd TEXT, " +
                                                                              "correctans INTEGER, " +
                                                                              "topic TEXT)";
    private static final String CREATE_TABLE_QUIZ = "CREATE TABLE Quiz(id INTEGER PRIMARY KEY, completed INTEGER, highest INTEGER)";
    private static final String CREATE_TABLE_REVIEW = "CREATE TABLE Review(id INTEGER PRIMARY KEY, recentans INTEGER, correctonce INTEGER)";
    private static final String CREATE_TABLE_POINTS = "CREATE TABLE Points(id INTEGER PRIMARY KEY, points INTEGER)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_TROPHY);
        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_QUIZ);
        db.execSQL(CREATE_TABLE_REVIEW);
        db.execSQL(CREATE_TABLE_POINTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Lesson");
        db.execSQL("DROP TABLE IF EXISTS Trophy");
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Quiz");
        db.execSQL("DROP TABLE IF EXISTS Review");
        onCreate(db);
    }

    public void addLesson(DBLesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", lesson.getId());
        values.put("viewed", lesson.getViewed());
        Log.e(TAG, "Lesson id " + lesson.getId() + " added");
        db.insert("Lesson", null, values);
        db.close();
    }

    public DBLesson getLesson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Lesson WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int lid = c.getInt(c.getColumnIndex("id"));
        int lviewed = c.getInt(c.getColumnIndex("viewed"));
        DBLesson lesson = new DBLesson(lid, lviewed);
        return lesson;
    }

    public void updateLesson(DBLesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("viewed", lesson.getViewed());
        db.update("Lesson", values, "id = ?", new String[]{String.valueOf(lesson.getId())});
    }

    public void addTrophy(DBTrophy trophy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", trophy.getId());
        values.put("earned", trophy.getEarned());
        Log.e(TAG, "Trophy id " + trophy.getId() + " added");
        db.insert("Trophy", null, values);
        db.close();
    }

    public DBTrophy getTrophy(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Trophy WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int tid = c.getInt(c.getColumnIndex("id"));
        int tearned = c.getInt(c.getColumnIndex("earned"));
        DBTrophy trophy = new DBTrophy(tid, tearned);
        return trophy;
    }

    public void updateTrophy(DBTrophy trophy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("earned", trophy.getEarned());
        db.update("Trophy", values, "id = ?", new String[]{String.valueOf(trophy.getId())});
    }

    public void addQuestion(DBQuestion question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", question.getId());
        values.put("question", question.getQuestion());
        values.put("answera", question.getAnswera());
        values.put("answerb", question.getAnswerb());
        values.put("answerc", question.getAnswerc());
        values.put("answerd", question.getAnswerd());
        values.put("correctans", question.getCorrectans());
        values.put("topic", question.getTopic());
        Log.e(TAG, "Question id " + question.getId() + " added");
        db.insert("Question", null, values);
        db.close();
    }

    public DBQuestion getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Question WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int qid = c.getInt(c.getColumnIndex("id"));
        String qquestion = c.getString(c.getColumnIndex("question"));
        String qanswera = c.getString(c.getColumnIndex("answera"));
        String qanswerb = c.getString(c.getColumnIndex("answerb"));
        String qanswerc = c.getString(c.getColumnIndex("answerc"));
        String qanswerd = c.getString(c.getColumnIndex("answerd"));
        int qcorrectans = c.getInt(c.getColumnIndex("correctans"));
        String qtopic = c.getString(c.getColumnIndex("topic"));
        DBQuestion question = new DBQuestion(qid, qquestion, qanswera, qanswerb, qanswerc, qanswerd, qcorrectans, qtopic);
        return question;
    }

    public void addQuiz(DBQuiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", quiz.getId());
        values.put("completed", quiz.getCompleted());
        values.put("highest", quiz.getHighest());
        Log.e(TAG, "Quiz id " + quiz.getId() + " added");
        db.insert("Quiz", null, values);
        db.close();
    }

    public DBQuiz getQuiz(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Quiz WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int qzid = c.getInt(c.getColumnIndex("id"));
        int qzcompleted = c.getInt(c.getColumnIndex("completed"));
        int qzhighest = c.getInt(c.getColumnIndex("highest"));
        DBQuiz quiz = new DBQuiz(qzid, qzcompleted, qzhighest);
        return quiz;
    }

    public void updateQuiz(DBQuiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", quiz.getCompleted());
        values.put("highest", quiz.getHighest());
        db.update("Quiz", values, "id = ?", new String[]{String.valueOf(quiz.getId())});
    }

    public void addReview(DBReview review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", review.getId());
        values.put("recentans", review.getRecentans());
        values.put("correctonce", review.getCorrectonce());
        Log.e(TAG, "Review id " + review.getId() + " added");
        db.insert("Review", null, values);
        db.close();
    }

    public DBReview getReview(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Review WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int rid = c.getInt(c.getColumnIndex("id"));
        int rrecentans = c.getInt(c.getColumnIndex("recentans"));
        int rcorrectonce = c.getInt(c.getColumnIndex("correctonce"));
        DBReview review = new DBReview(rid, rrecentans, rcorrectonce);
        return review;
    }

    public void updateReview(DBReview review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("recentans", review.getRecentans());
        values.put("correctonce", review.getCorrectonce());
        db.update("Review", values, "id = ?", new String[]{String.valueOf(review.getId())});
    }

    public void addPoints(DBPoints points) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", points.getId());
        values.put("points", points.getPoints());
        Log.e(TAG, "Points id " + points.getId() + " added");
        db.insert("Points", null, values);
        db.close();
    }

    public DBPoints getPoints(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Points WHERE id = " + id;
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }
        int pid = c.getInt(c.getColumnIndex("id"));
        int ppoints = c.getInt(c.getColumnIndex("points"));
        DBPoints points = new DBPoints(pid, ppoints);
        return points;
    }

    public void updatePoints(DBPoints points) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("points", points.getPoints());
        db.update("Points", values, "id = ?", new String[]{String.valueOf(points.getId())});
    }
}
