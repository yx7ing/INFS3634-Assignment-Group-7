package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz1Complete extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Quiz1Complete";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1_complete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set selected item in navigation drawer on start
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);

        // Set and format title textview
        TextView textView = findViewById(R.id.quiz1cTextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Get extra intent data, containing the array of answers
        // Int to count how many correct answers
        // Int total points from quiz, only for getting a question correct the first time
        // 75 points for a correct answer
        // 100 extra points for getting all questions correct on the first attempt of the quiz
        // If this is the first attempt of the quiz, reward user the silver trophy
        // Update database to show the quiz is completed and store their mark
        // If not the first attempt, update the mark if it is a new highest
        // If this is the first time the user got full marks, reward user the gold trophy
        Bundle extras = getIntent().getExtras();
        int[] answers = extras.getIntArray("answers");
        int marks = 0;
        int points = 0;
        for (int i = 0; i < answers.length; i++) {
            // If correct, add a mark
            // If correct and never answered correctly before, add 75 points
            // Regardless of whether answered correctly before, set to answered correctly now and update most recent answer
            // If incorrect, update answer, maintain answered correctly status
            if (answers[i] == db.getQuestion(i+1).getCorrectans()) {
                marks++;
                if (db.getReview(i+1).getCorrectonce() == 0) {
                    points += 75;
                }
                db.updateReview(new DBReview(i+1, answers[i], 1));
            } else {
                db.updateReview(new DBReview(i+1, answers[i], db.getReview(i+1).getCorrectonce()));
            }
        }
        if (marks == 12 && db.getQuiz(1).getCompleted() == 0){
            points += 100;
        }
        db.updatePoints(new DBPoints(1, db.getPoints(1).getPoints()+points));

        Log.e(TAG, "Points earned for this quiz: " + points);
        Log.e(TAG, "Total points: " + db.getPoints(1).getPoints());

        if (db.getQuiz(1).getCompleted() == 0){
            db.updateQuiz(new DBQuiz(1, 1, marks));
            db.updateTrophy(new DBTrophy(21, 1));
            Toast.makeText(this, "You got a trophy for completing Quiz 1!", Toast.LENGTH_LONG).show();
        } else {
            if (db.getQuiz(1).getHighest() < marks) {
                db.updateQuiz(new DBQuiz(1, 1, marks));
            }
        }
        Log.e(TAG, "Quiz Trophy 1 status is " + db.getTrophy(21).getEarned());

        if (marks == 12 && db.getTrophy(31).getEarned() == 0) {
            db.updateTrophy(new DBTrophy(31, 1));
            Toast.makeText(this, "You got a trophy for a perfect mark in Quiz 1!", Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "Full Marks Trophy 1 status is " + db.getTrophy(31).getEarned());

        // Set and format result textview
        TextView result = (TextView) findViewById(R.id.quiz1cTextView2);
        result.setText("Congratulations! You got " + marks + "/12 questions correct and earned " + points + " points.\n\n\n" +
                "See the review page for a breakdown of how you did in this quiz and possible areas of improvement.");
        result.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, Home.class);
            startActivity(i);
        } else if (id == R.id.nav_lessons) {
            Intent i = new Intent(this, Lessons.class);
            startActivity(i);
        } else if (id == R.id.nav_review) {
            Intent i = new Intent(this, Review.class);
            startActivity(i);
        } else if (id == R.id.nav_quiz) {
            Intent i = new Intent(this, Quiz.class);
            startActivity(i);
        } else if (id == R.id.nav_accolades) {
            Intent i = new Intent(this, Accolades.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}