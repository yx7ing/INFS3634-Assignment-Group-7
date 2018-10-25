package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lesson1_1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson1_1";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Update the database to show this lesson has been viewed
        Log.e(TAG, TAG + " view status is " + db.getLesson(101).getViewed());
        db.updateLesson(new DBLesson(101, 1));
        Log.e(TAG, TAG + " view status is " + db.getLesson(101).getViewed());

        // If all sub-lessons have been viewed
        // AND the trophy for the lesson has not yet been earned
        // reward it to the user and show a toast
        if (db.getTrophy(11).getEarned() == 0
                && db.getLesson(101).getViewed() == 1
                && db.getLesson(102).getViewed() == 1
                && db.getLesson(103).getViewed() == 1
                && db.getLesson(104).getViewed() == 1
                && db.getLesson(105).getViewed() == 1
                && db.getLesson(106).getViewed() == 1) {
            db.updateTrophy(new DBTrophy(11, 1));
            Toast.makeText(this, "You got a trophy for completing Lesson 1!", Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "Trophy 1 status is " + db.getTrophy(11).getEarned());

        setContentView(R.layout.activity_lesson1_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("INFS3617 Study Helper");

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
        TextView textView = findViewById(R.id.lesson1_1TextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Create string array to store content
        // Add content to array adapter

        // Override getView method to set custom formatting of rows
        // (in this case only affects first item, the title)

        // In the else{}, this is a check for when the listview is scrolled through
        // which updates the rows. This section of code is necessary to ensure only
        // the initially formatted rows are bolded/enlarged and maintain default
        // formatting for other rows, i.e. resetting their typeface and font size.

        // Populate listview with adapter

        String content[] = {"\nWhat is Networking?", "Networking is the practice of sharing data through " +
                "a shared medium in an information system, between different nodes. It includes the design, " +
                "construction and use of the network along with its maintenance, management and operation, " +
                "especially regarding protocols, software and wired and wireless technology."};
        final ListView listView = findViewById(R.id.lesson1_1ListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Lesson1_1.this,android.R.layout.simple_list_item_1,content){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                TextView item = (TextView) super.getView(position,convertView,parent);
                if (position == 0){
                    item.setTypeface(item.getTypeface(), Typeface.BOLD_ITALIC);
                    item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                } else {
                    item.setTypeface(null,Typeface.NORMAL);
                    item.setTextSize(14);
                }
                return item;
            }
        };
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);
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