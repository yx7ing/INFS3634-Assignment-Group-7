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

import java.util.ArrayList;

public class Lesson2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson2";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);

        TextView title = (TextView) findViewById(R.id.lesson2TextView);
        title.setText("Lesson 2");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        TextView subtitle = (TextView) findViewById(R.id.lesson2TextView2);
        subtitle.setText("Physical and Data Link Layer");
        subtitle.setTextColor(Color.parseColor("#FF9933"));
        subtitle.setTypeface(title.getTypeface(), Typeface.BOLD);
        subtitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        String[] viewed = new String[7];
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(201+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(201+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "2.1 - OSI Physical Layer");
        content.add(viewed[1] + "2.2 - Guided and Unguided Media");
        content.add(viewed[2] + "2.3 - Shielded and Unshielded Twisted Pair");
        content.add(viewed[3] + "2.4 - Coaxial Cable");
        content.add(viewed[4] + "2.5 - Optical Fiber Cable");
        content.add(viewed[5] + "2.6 - Network Topologies");
        content.add(viewed[6] + "2.7 - Data Link Layer - MAC Layer and LLC Layer");
        ListView lesson2List = (ListView) findViewById(R.id.lesson2ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson2List.setAdapter(adapter);
        lesson2List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Lesson2.this, Lesson2_1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Lesson2.this, Lesson2_2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Lesson2.this, Lesson2_3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Lesson2.this, Lesson2_4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Lesson2.this, Lesson2_5.class);
                        startActivity(i4);
                        break;
                    case 5: Intent i5 = new Intent(Lesson2.this, Lesson2_6.class);
                        startActivity(i5);
                        break;
                    case 6: Intent i6 = new Intent(Lesson2.this, Lesson2_7.class);
                        startActivity(i6);
                        break;
                    default: return;
                }
            }
        });

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);

        String[] viewed = new String[7];
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(201+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(201+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "2.1 - OSI Physical Layer");
        content.add(viewed[1] + "2.2 - Guided and Unguided Media");
        content.add(viewed[2] + "2.3 - Shielded and Unshielded Twisted Pair");
        content.add(viewed[3] + "2.4 - Coaxial Cable");
        content.add(viewed[4] + "2.5 - Optical Fiber Cable");
        content.add(viewed[5] + "2.6 - Network Topologies");
        content.add(viewed[6] + "2.7 - Data Link Layer - MAC Layer and LLC Layer");
        ListView lesson2List = (ListView) findViewById(R.id.lesson2ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson2List.setAdapter(adapter);
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