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

import java.util.ArrayList;

public class Quiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Quiz";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
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
        TextView title = (TextView) findViewById(R.id.quizTextView);
        title.setText("Quiz");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Set warning textview text
        TextView warning = (TextView) findViewById(R.id.quizTextView2);
        warning.setText("Warning: Exiting this app or moving to another screen during a quiz will cause you to lose all progress.");

        // Check with database
        // whether each quiz has been attempted before,
        // and the highest mark earned for that quiz
        String[] completed = new String[5];
        String[] highest = new String[5];
        for (int i = 0; i < 5; i++) {
            if (db.getQuiz(i+1).getCompleted() == 0) {
                completed[i] = "[   ] ";
            } else if (db.getQuiz(i+1).getCompleted() == 1) {
                completed[i] = "[✓] ";
            }
            highest[i] = db.getQuiz(i+1).getHighest() + "/12";
        }

        // Create list of quizzes, completed status and highest mark
        // Manually add the combined quiz
        // Add them to an array adapter
        // Populate the listview using the adapter
        // Handle clicks on each listview item
        ArrayList<String> content = new ArrayList<String>();
        content.add(completed[0] + "Lesson 1     Highest Mark: " + highest[0]);
        content.add(completed[1] + "Lesson 2     Highest Mark: " + highest[1]);
        content.add(completed[2] + "Lesson 3     Highest Mark: " + highest[2]);
        content.add(completed[3] + "Lesson 4     Highest Mark: " + highest[3]);
        content.add(completed[4] + "Lesson 5     Highest Mark: " + highest[4]);
        content.add("[   ] " + "Combined Quiz");
        ListView quizList = (ListView) findViewById(R.id.quizListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        quizList.setAdapter(adapter);
        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent. getItemAtPosition(position));
                switch (position) {
                    case 0: Intent i0 = new Intent(Quiz.this, Quiz1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Quiz.this, Quiz2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Quiz.this, Quiz3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Quiz.this, Quiz4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Quiz.this, Quiz5.class);
                        startActivity(i4);
                        break;
                    case 5: Intent i5 = new Intent(Quiz.this, QuizCombined.class);
                        startActivity(i5);
                        break;
                    default: return;
                }
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);

        // Same as onCreate, for when user presses back from another activity to this activity
        String[] completed = new String[5];
        String[] highest = new String[5];
        for (int i = 0; i < 5; i++) {
            if (db.getQuiz(i+1).getCompleted() == 0) {
                completed[i] = "[   ] ";
            } else if (db.getQuiz(i+1).getCompleted() == 1) {
                completed[i] = "[✓] ";
            }
            highest[i] = db.getQuiz(i+1).getHighest() + "/12";
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(completed[0] + "Lesson 1     Highest Mark: " + highest[0]);
        content.add(completed[1] + "Lesson 2     Highest Mark: " + highest[1]);
        content.add(completed[2] + "Lesson 3     Highest Mark: " + highest[2]);
        content.add(completed[3] + "Lesson 4     Highest Mark: " + highest[3]);
        content.add(completed[4] + "Lesson 5     Highest Mark: " + highest[4]);
        content.add("[   ] " + "Combined Quiz");
        ListView quizList = (ListView) findViewById(R.id.quizListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        quizList.setAdapter(adapter);
        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent. getItemAtPosition(position));
                switch (position) {
                    case 0: Intent i0 = new Intent(Quiz.this, Quiz1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Quiz.this, Quiz2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Quiz.this, Quiz3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Quiz.this, Quiz4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Quiz.this, Quiz5.class);
                        startActivity(i4);
                        break;
                    case 5: Intent i5 = new Intent(Quiz.this, QuizCombined.class);
                        startActivity(i5);
                        break;
                    default: return;
                }
            }
        });
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
            // Current page
        } else if (id == R.id.nav_accolades) {
            Intent i = new Intent(this, Accolades.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}