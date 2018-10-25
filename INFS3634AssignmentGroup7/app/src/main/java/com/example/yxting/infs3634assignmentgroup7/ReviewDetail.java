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
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class ReviewDetail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ReviewDetail";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
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
        navigationView.getMenu().getItem(3).setChecked(true);

        // Get intent extra data
        Bundle extra = getIntent().getExtras();
        int quizNum = extra.getInt("quizNum");

        // Set and format title textview
        TextView title = (TextView) findViewById(R.id.reviewDTextView);
        title.setText("Lesson " + quizNum + " Quiz Review");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Multiple arrays of size 12 to store info from database
        // about each question from this quiz
        // The question number and question
        // The 4 options
        // The user's latest response
        // The correct answer
        // A recommendation on what to study - the questions related topic
        // The aggregated content array
        String questions[] = new String[12];
        String answera[] = new String[12];
        String answerb[] = new String[12];
        String answerc[] = new String[12];
        String answerd[] = new String[12];
        String userans[] = new String[12];
        String correctans[] = new String[12];
        String reltopic[] = new String[12];
        String content[] = new String[12];

        for (int i = 0; i < 12; i++){
            questions[i] = db.getQuestion((quizNum-1)*12 + i + 1).getQuestion();
            answera[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswera();
            answerb[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerb();
            answerc[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerc();
            answerd[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerd();

            int recentans = db.getReview((quizNum-1)*12 + i + 1).getRecentans();
            if (recentans == 1){
                userans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswera();
            } else if (recentans == 2) {
                userans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerb();
            } else if (recentans == 3) {
                userans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerc();
            } else if (recentans == 4) {
                userans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerd();
            }

            int correct = db.getQuestion((quizNum-1)*12 + i + 1).getCorrectans();
            if (correct == 1){
                correctans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswera();
            } else if (correct == 2) {
                correctans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerb();
            } else if (correct == 3) {
                correctans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerc();
            } else if (correct == 4) {
                correctans[i] = db.getQuestion((quizNum-1)*12 + i + 1).getAnswerd();
            }

            reltopic[i] = db.getQuestion((quizNum-1)*12 + i + 1).getTopic();
        }

        for (int i = 0; i < 12; i++) {
            content[i] = questions[i] + "\n";
            content[i] += answera[i] + "\n";
            content[i] += answerb[i] + "\n";
            content[i] += answerc[i] + "\n";
            content[i] += answerd[i] + "\n\n";
            content[i] += "Your answer:\n" + userans[i] + "\n";
            content[i] += "Correct answer:\n" + correctans[i] + "\n";
            if (!userans[i].equals(correctans[i])) {
                content[i] += "\nRelated topic for further revision:\n" + reltopic[i] + "\n";
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        ListView list = (ListView) findViewById(R.id.reviewDListView);
        list.setAdapter(adapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(3).setChecked(true);
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