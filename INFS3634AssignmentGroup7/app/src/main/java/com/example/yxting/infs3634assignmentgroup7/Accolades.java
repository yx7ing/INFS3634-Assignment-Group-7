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
import android.widget.CheckBox;
import android.widget.TextView;

public class Accolades extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Accolades";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accolades);
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
        navigationView.getMenu().getItem(4).setChecked(true);

        // Set and format static textviews
        TextView title = (TextView) findViewById(R.id.accoladesTextView);
        title.setText("Accolades");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
        TextView Bronze = (TextView) findViewById(R.id.accoladesTextView6);
        Bronze.setText("Lessons");
        Bronze.setTextColor(Color.parseColor("#003399"));
        Bronze.setTypeface(Bronze.getTypeface(), Typeface.BOLD);
        Bronze.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        TextView Silver = (TextView) findViewById(R.id.accoladesTextView7);
        Silver.setText("Quizzes");
        Silver.setTextColor(Color.parseColor("#003399"));
        Silver.setTypeface(Silver.getTypeface(), Typeface.BOLD);
        Silver.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        TextView Gold = (TextView) findViewById(R.id.accoladesTextView8);
        Gold.setText("100% Quizzes");
        Gold.setTextColor(Color.parseColor("#003399"));
        Gold.setTypeface(Gold.getTypeface(), Typeface.BOLD);
        Gold.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        TextView Points = (TextView) findViewById(R.id.accoladesTextView2);
        Points.setText("Points");
        Points.setTextColor(Color.parseColor("#003399"));
        Points.setTypeface(Points.getTypeface(), Typeface.BOLD);
        Points.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        TextView Medals = (TextView) findViewById(R.id.accoladesTextView4);
        Medals.setText("Medals");
        Medals.setTextColor(Color.parseColor("#003399"));
        Medals.setTypeface(Medals.getTypeface(), Typeface.BOLD);
        Medals.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        // Set and format dynamic text views
        TextView PointsValue = (TextView) findViewById(R.id.accoladesTextView3);
        int numPoints = db.getPoints(1).getPoints();
        Log.e(TAG, "Points: " + numPoints);
        PointsValue.setText(Integer.toString(numPoints));
        PointsValue.setTextColor(Color.parseColor("#003399"));
        PointsValue.setTypeface(PointsValue.getTypeface(), Typeface.BOLD);
        PointsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView MedalsValue = (TextView) findViewById(R.id.accoladesTextView5);
        int numMedals = numPoints/1000;
        Log.e(TAG, "Medals: " + numMedals);
        MedalsValue.setText(Integer.toString(numMedals));
        MedalsValue.setTextColor(Color.parseColor("#003399"));
        MedalsValue.setTypeface(MedalsValue.getTypeface(), Typeface.BOLD);
        MedalsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        // Create an array for each group of checkboxes
        // Disable them all
        // Set them according to whether the corresponding trophy has been earned
        CheckBox bronzeCheckBox[] = {
                findViewById(R.id.checkBoxB1),
                findViewById(R.id.checkBoxB2),
                findViewById(R.id.checkBoxB3),
                findViewById(R.id.checkBoxB4),
                findViewById(R.id.checkBoxB5),
        };
        CheckBox silverCheckBox[] = {
                findViewById(R.id.checkBoxS1),
                findViewById(R.id.checkBoxS2),
                findViewById(R.id.checkBoxS3),
                findViewById(R.id.checkBoxS4),
                findViewById(R.id.checkBoxS5),
        };
        CheckBox goldCheckBox[] = {
                findViewById(R.id.checkBoxG1),
                findViewById(R.id.checkBoxG2),
                findViewById(R.id.checkBoxG3),
                findViewById(R.id.checkBoxG4),
                findViewById(R.id.checkBoxG5),
        };
        for (int i = 0; i < 5; i++){
            bronzeCheckBox[i].setEnabled(false);
            silverCheckBox[i].setEnabled(false);
            goldCheckBox[i].setEnabled(false);
        }

        for (int i = 0; i < 5; i++){
            if (db.getTrophy(11+i).getEarned() == 1) {
                bronzeCheckBox[i].setChecked(true);
            } else {
                bronzeCheckBox[i].setChecked(false);
            }
        }
        for (int i = 0; i < 5; i++){
            if (db.getTrophy(21+i).getEarned() == 1) {
                silverCheckBox[i].setChecked(true);
            } else {
                silverCheckBox[i].setChecked(false);
            }
        }
        for (int i = 0; i < 5; i++){
            if (db.getTrophy(31+i).getEarned() == 1) {
                goldCheckBox[i].setChecked(true);
            } else {
                goldCheckBox[i].setChecked(false);
            }
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(4).setChecked(true);

        // Same as onCreate, reset values in case points or trophies have been earned
        TextView PointsValue = (TextView) findViewById(R.id.accoladesTextView3);
        int numPoints = db.getPoints(1).getPoints();
        Log.e(TAG, "Points: " + numPoints);
        PointsValue.setText(Integer.toString(numPoints));
        PointsValue.setTextColor(Color.parseColor("#003399"));
        PointsValue.setTypeface(PointsValue.getTypeface(), Typeface.BOLD);
        PointsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView MedalsValue = (TextView) findViewById(R.id.accoladesTextView5);
        int numMedals = numPoints/1000;
        Log.e(TAG, "Medals: " + numMedals);
        MedalsValue.setText(Integer.toString(numMedals));
        MedalsValue.setTextColor(Color.parseColor("#003399"));
        MedalsValue.setTypeface(MedalsValue.getTypeface(), Typeface.BOLD);
        MedalsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        CheckBox bronzeCheckBox[] = {
                findViewById(R.id.checkBoxB1),
                findViewById(R.id.checkBoxB2),
                findViewById(R.id.checkBoxB3),
                findViewById(R.id.checkBoxB4),
                findViewById(R.id.checkBoxB5),
        };
        CheckBox silverCheckBox[] = {
                findViewById(R.id.checkBoxS1),
                findViewById(R.id.checkBoxS2),
                findViewById(R.id.checkBoxS3),
                findViewById(R.id.checkBoxS4),
                findViewById(R.id.checkBoxS5),
        };
        CheckBox goldCheckBox[] = {
                findViewById(R.id.checkBoxG1),
                findViewById(R.id.checkBoxG2),
                findViewById(R.id.checkBoxG3),
                findViewById(R.id.checkBoxG4),
                findViewById(R.id.checkBoxG5),
        };
        for (int i = 0; i < 5; i++){
            bronzeCheckBox[i].setEnabled(false);
            silverCheckBox[i].setEnabled(false);
            goldCheckBox[i].setEnabled(false);
        }

        for (int i = 0; i < 5; i++){
            if (db.getTrophy(11+i).getEarned() == 1) {
                bronzeCheckBox[i].setChecked(true);
            } else {
                bronzeCheckBox[i].setChecked(false);
            }
        }
        for (int i = 0; i < 5; i++){
            if (db.getTrophy(21+i).getEarned() == 1) {
                silverCheckBox[i].setChecked(true);
            } else {
                silverCheckBox[i].setChecked(false);
            }
        }
        for (int i = 0; i < 5; i++){
            if (db.getTrophy(31+i).getEarned() == 1) {
                goldCheckBox[i].setChecked(true);
            } else {
                goldCheckBox[i].setChecked(false);
            }
        }
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
            // Current page
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}