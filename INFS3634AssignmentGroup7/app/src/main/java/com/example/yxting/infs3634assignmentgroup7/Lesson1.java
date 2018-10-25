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

public class Lesson1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson1";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
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
        TextView title = (TextView) findViewById(R.id.lesson1TextView);
        title.setText("Lesson 1");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Set and format subtitle textview
        TextView subtitle = (TextView) findViewById(R.id.lesson1TextView2);
        subtitle.setText("Introduction to Networking and the OSI Model");
        subtitle.setTextColor(Color.parseColor("#FF9933"));
        subtitle.setTypeface(title.getTypeface(), Typeface.BOLD);
        subtitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Check with the database
        // whether each topic in this lesson has been viewed before
        // 0 = false, i.e. not viewed
        // 1 = true, i.e. viewed
        // add the true/false to an array of strings for the next step
        String[] viewed = new String[6];
        for (int i = 0; i < 6; i++) {
            if (db.getLesson(101+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(101+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        // Create list of subtopics and their status (whether the user has viewed it before or not)
        // Add them to a an array adapter
        // Populate the listview using the adapter
        // Handle clicks on each listview item
        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "1.1 - What Is Networking?");
        content.add(viewed[1] + "1.2 - Introduction to OSI Model");
        content.add(viewed[2] + "1.3 - Networking Architecture - Client Server Model");
        content.add(viewed[3] + "1.4 - Peer to Peer Network");
        content.add(viewed[4] + "1.5 - Analog Transmission");
        content.add(viewed[5] + "1.6 - Data Transmission");
        ListView lesson1List = (ListView) findViewById(R.id.lesson1ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson1List.setAdapter(adapter);
        lesson1List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Lesson1.this, Lesson1_1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Lesson1.this, Lesson1_2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Lesson1.this, Lesson1_3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Lesson1.this, Lesson1_4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Lesson1.this, Lesson1_5.class);
                        startActivity(i4);
                        break;
                    case 5: Intent i5 = new Intent(Lesson1.this, Lesson1_6.class);
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
        navigationView.getMenu().getItem(1).setChecked(true);

        // Same as on create, update when returning to this activity in case a change has been made
        String[] viewed = new String[6];
        for (int i = 0; i < 6; i++) {
            if (db.getLesson(101+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(101+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }
        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "1.1 - What Is Networking?");
        content.add(viewed[1] + "1.2 - Introduction to OSI Model");
        content.add(viewed[2] + "1.3 - Networking Architecture - Client Server Model");
        content.add(viewed[3] + "1.4 - Peer to Peer Network");
        content.add(viewed[4] + "1.5 - Analog Transmission");
        content.add(viewed[5] + "1.6 - Data Transmission");
        ListView lesson1List = (ListView) findViewById(R.id.lesson1ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson1List.setAdapter(adapter);
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
