package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

public class Lessons extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lessons";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
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
        navigationView.getMenu().getItem(1).setChecked(true);

        // Set and format title textview
        TextView title = (TextView) findViewById(R.id.lessonsTextView);
        title.setText("Lessons");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Check with the database
        // whether each lesson has been completed
        // i.e. all sub-lessons viewed
        // Instead of checking each sub-lesson
        // we can just check the trophy for the lesson
        // 0 = false, i.e. not completed
        // 1 = true, i.e. completed
        // add the true/false to an array of strings for the next step
        String[] viewed = new String[5];
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(11+i).getEarned() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getTrophy(11+i).getEarned() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        // Create list of lessons and their status (whether the user has viewed all the subtopics in the lesson)
        // Add the list to an array adapter
        // Populate the listview using the adapter
        // Handle clicks on each listview item
        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "1. Introduction to Networking and the OSI Model");
        content.add(viewed[1] + "2. Physical and Data Link Layer");
        content.add(viewed[2] + "3. Network Layer");
        content.add(viewed[3] + "4. Routing");
        content.add(viewed[4] + "5. TCP/UDP and Application Layer");
        ListView lessonList = (ListView) findViewById(R.id.lessonsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lessonList.setAdapter(adapter);
        lessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Lessons.this, Lesson1.class);
                            startActivity(i0);
                            break;
                    case 1: Intent i1 = new Intent(Lessons.this, Lesson2.class);
                            startActivity(i1);
                            break;
                    case 2: Intent i2 = new Intent(Lessons.this, Lesson3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Lessons.this, Lesson4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Lessons.this, Lesson5.class);
                        startActivity(i4);
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
        navigationView.getMenu().getItem(1).setChecked(true);

        // Same as on create, update when returning to this activity in case a change has been made
        String[] viewed = new String[5];
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(11+i).getEarned() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getTrophy(11+i).getEarned() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "1. Introduction to Networking and the OSI Model");
        content.add(viewed[1] + "2. Physical and Data Link Layer");
        content.add(viewed[2] + "3. Network Layer");
        content.add(viewed[3] + "4. Routing");
        content.add(viewed[4] + "5. TCP/UDP and Application Layer");
        ListView lessonList = (ListView) findViewById(R.id.lessonsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lessonList.setAdapter(adapter);
        lessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Lessons.this, Lesson1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Lessons.this, Lesson2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Lessons.this, Lesson3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Lessons.this, Lesson4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Lessons.this, Lesson5.class);
                        startActivity(i4);
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
            // Current page
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