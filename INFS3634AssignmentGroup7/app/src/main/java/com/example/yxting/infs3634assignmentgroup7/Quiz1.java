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

public class Quiz1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Quiz1";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
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
        TextView textView = findViewById(R.id.quiz1TextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // A modifiable final int (in an array) to count up to 12 questions
        // String array to store the question and 4 answers retrieved from database
        // Create an array adapter using the array as content
        // Populate the listview using the adapter

        // An array of ints to store the user's answers
        // Handling clicks on listview items
        // Only handle clicks on answers, not the question
        // On click, store the answer
        // Increment the question number and update the content array
        // Notify the adapter to update the display
        // After answering all 12 questions move to the result screen
        final int[] questionNum = {1};
        final String[] qna = new String[5];
        qna[0] = "Question " + questionNum[0] + ". " + db.getQuestion(questionNum[0]).getQuestion() + "\n";
        qna[1] = db.getQuestion(questionNum[0]).getAnswera() + "\n";
        qna[2] = db.getQuestion(questionNum[0]).getAnswerb() + "\n";
        qna[3] = db.getQuestion(questionNum[0]).getAnswerc() + "\n";
        qna[4] = db.getQuestion(questionNum[0]).getAnswerd() + "\n";
        final int[] answers = new int[12];
        ListView listView = (ListView) findViewById(R.id.quiz1ListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, qna);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    answers[questionNum[0]-1] = position;
                    if (questionNum[0] < 12) {
                        questionNum[0]++;
                        qna[0] = "Question " + questionNum[0] + ". " + db.getQuestion(questionNum[0]).getQuestion() + "\n";
                        qna[1] = db.getQuestion(questionNum[0]).getAnswera() + "\n";
                        qna[2] = db.getQuestion(questionNum[0]).getAnswerb() + "\n";
                        qna[3] = db.getQuestion(questionNum[0]).getAnswerc() + "\n";
                        qna[4] = db.getQuestion(questionNum[0]).getAnswerd() + "\n";
                        arrayAdapter.notifyDataSetChanged();
                    } else {
                        Intent i = new Intent(Quiz1.this, Quiz1Complete.class);
                        i.putExtra("answers", answers);
                        startActivity(i);
                    }
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

        // Same as onCreate, for resetting the quiz when pressing back from another activity to return to this quiz
        final int[] questionNum = {1};
        final String[] qna = new String[5];
        qna[0] = "Question " + questionNum[0] + ". " + db.getQuestion(questionNum[0]).getQuestion() + "\n";
        qna[1] = db.getQuestion(questionNum[0]).getAnswera() + "\n";
        qna[2] = db.getQuestion(questionNum[0]).getAnswerb() + "\n";
        qna[3] = db.getQuestion(questionNum[0]).getAnswerc() + "\n";
        qna[4] = db.getQuestion(questionNum[0]).getAnswerd() + "\n";
        final int[] answers = new int[12];
        ListView listView = (ListView) findViewById(R.id.quiz1ListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, qna);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    answers[questionNum[0]-1] = position;
                    if (questionNum[0] < 12) {
                        questionNum[0]++;
                        qna[0] = "Question " + questionNum[0] + ". " + db.getQuestion(questionNum[0]).getQuestion() + "\n";
                        qna[1] = db.getQuestion(questionNum[0]).getAnswera() + "\n";
                        qna[2] = db.getQuestion(questionNum[0]).getAnswerb() + "\n";
                        qna[3] = db.getQuestion(questionNum[0]).getAnswerc() + "\n";
                        qna[4] = db.getQuestion(questionNum[0]).getAnswerd() + "\n";
                        arrayAdapter.notifyDataSetChanged();
                    } else {
                        Intent i = new Intent(Quiz1.this, Quiz1Complete.class);
                        i.putExtra("answers", answers);
                        startActivity(i);
                    }
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
