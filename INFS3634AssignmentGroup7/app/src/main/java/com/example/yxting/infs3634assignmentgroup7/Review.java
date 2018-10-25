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
import android.widget.Toast;

import java.lang.reflect.Array;

public class Review extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Review";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
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

        // Set and format title textview
        TextView title = (TextView) findViewById(R.id.reviewTextView);
        title.setText("Review");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Set info textview text
        TextView warning = (TextView) findViewById(R.id.reviewTextView2);
        warning.setText("Review your responses from the latest attempt of each quiz.");

        // Create string array to store listview content
        // Create array adapter using the string array
        // Populate the listview with the adapter
        String content[] = new String[5];
        content[0] = "Lesson 1 Quiz";
        content[1] = "Lesson 2 Quiz";
        content[2] = "Lesson 3 Quiz";
        content[3] = "Lesson 4 Quiz";
        content[4] = "Lesson 5 Quiz";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        ListView list = (ListView) findViewById(R.id.reviewListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Review.this, ReviewDetail.class);
                        if (db.getQuiz(position+1).getCompleted() == 0) {
                            Toast.makeText(Review.this, "You haven't attempted this quiz yet.", Toast.LENGTH_LONG).show();
                        } else {
                            i0.putExtra("quizNum", position+1);
                            startActivity(i0);
                        }
                        break;
                    case 1: Intent i1 = new Intent(Review.this, ReviewDetail.class);
                        if (db.getQuiz(position+1).getCompleted() == 0) {
                            Toast.makeText(Review.this, "You haven't attempted this quiz yet.", Toast.LENGTH_LONG).show();
                        } else {
                            i1.putExtra("quizNum", position+1);
                            startActivity(i1);
                        }
                        break;
                    case 2: Intent i2 = new Intent(Review.this, ReviewDetail.class);
                        if (db.getQuiz(position+1).getCompleted() == 0) {
                            Toast.makeText(Review.this, "You haven't attempted this quiz yet.", Toast.LENGTH_LONG).show();
                        } else {
                            i2.putExtra("quizNum", position+1);
                            startActivity(i2);
                        }
                        break;
                    case 3: Intent i3 = new Intent(Review.this, ReviewDetail.class);
                        if (db.getQuiz(position+1).getCompleted() == 0) {
                            Toast.makeText(Review.this, "You haven't attempted this quiz yet.", Toast.LENGTH_LONG).show();
                        } else {
                            i3.putExtra("quizNum", position+1);
                            startActivity(i3);
                        }
                        break;
                    case 4: Intent i4 = new Intent(Review.this, ReviewDetail.class);
                        if (db.getQuiz(position+1).getCompleted() == 0) {
                            Toast.makeText(Review.this, "You haven't attempted this quiz yet.", Toast.LENGTH_LONG).show();
                        } else {
                            i4.putExtra("quizNum", position+1);
                            startActivity(i4);
                        }
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
            // Current page
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