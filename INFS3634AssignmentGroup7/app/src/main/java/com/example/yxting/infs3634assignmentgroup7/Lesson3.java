package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Lesson3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson3";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3);
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

        TextView title = (TextView) findViewById(R.id.lesson3TextView);
        title.setText("Lesson 3");
        title.setTextColor(Color.parseColor("#FF9933"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        TextView subtitle = (TextView) findViewById(R.id.lesson3TextView2);
        subtitle.setText("Network Layer");
        subtitle.setTextColor(Color.parseColor("#FF9933"));
        subtitle.setTypeface(title.getTypeface(), Typeface.BOLD);
        subtitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        String[] viewed = new String[12];
        for (int i = 0; i < 12; i++) {
            if (db.getLesson(301+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(301+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "3.1 - Hubs, Routers, Switches and Bridges");
        content.add(viewed[1] + "3.2 - Collision Domains");
        content.add(viewed[2] + "3.3 - Broadcast Domains");
        content.add(viewed[3] + "3.4 - Network Layer");
        content.add(viewed[4] + "3.5 - Logical Addressing");
        content.add(viewed[5] + "3.6 - IPv4");
        content.add(viewed[6] + "3.7 - IPv6");
        content.add(viewed[7] + "3.8 - Subnetting");
        content.add(viewed[8] + "3.9 - DHCP");
        content.add(viewed[9] + "3.10 - NAT");
        content.add(viewed[10] + "3.11 - ARP");
        content.add(viewed[11] + "3.12 - ICMP");
        ListView lesson3List = (ListView) findViewById(R.id.lesson3ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson3List.setAdapter(adapter);
        lesson3List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(Lesson3.this, Lesson3_1.class);
                        startActivity(i0);
                        break;
                    case 1: Intent i1 = new Intent(Lesson3.this, Lesson3_2.class);
                        startActivity(i1);
                        break;
                    case 2: Intent i2 = new Intent(Lesson3.this, Lesson3_3.class);
                        startActivity(i2);
                        break;
                    case 3: Intent i3 = new Intent(Lesson3.this, Lesson3_4.class);
                        startActivity(i3);
                        break;
                    case 4: Intent i4 = new Intent(Lesson3.this, Lesson3_5.class);
                        startActivity(i4);
                        break;
                    case 5: Intent i5 = new Intent(Lesson3.this, Lesson3_6.class);
                        startActivity(i5);
                        break;
                    case 6: Intent i6 = new Intent(Lesson3.this, Lesson3_7.class);
                        startActivity(i6);
                        break;
                    case 7: Intent i7 = new Intent(Lesson3.this, Lesson3_8.class);
                        startActivity(i7);
                        break;
                    case 8: Intent i8 = new Intent(Lesson3.this, Lesson3_9.class);
                        startActivity(i8);
                        break;
                    case 9: Intent i9 = new Intent(Lesson3.this, Lesson3_10.class);
                        startActivity(i9);
                        break;
                    case 10: Intent i10 = new Intent(Lesson3.this, Lesson3_11.class);
                        startActivity(i10);
                        break;
                    case 11: Intent i11 = new Intent(Lesson3.this, Lesson3_12.class);
                        startActivity(i11);
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

        String[] viewed = new String[12];
        for (int i = 0; i < 12; i++) {
            if (db.getLesson(301+i).getViewed() == 0) {
                viewed[i] = "[   ] ";
            } else if (db.getLesson(301+i).getViewed() == 1) {
                viewed[i] = "[✓] ";
            }
        }

        ArrayList<String> content = new ArrayList<String>();
        content.add(viewed[0] + "3.1 - Hubs, Routers, Switches and Bridges");
        content.add(viewed[1] + "3.2 - Collision Domains");
        content.add(viewed[2] + "3.3 - Broadcast Domains");
        content.add(viewed[3] + "3.4 - Network Layer");
        content.add(viewed[4] + "3.5 - Logical Addressing");
        content.add(viewed[5] + "3.6 - IPv4");
        content.add(viewed[6] + "3.7 - IPv6");
        content.add(viewed[7] + "3.8 - Subnetting");
        content.add(viewed[8] + "3.9 - DHCP");
        content.add(viewed[9] + "3.10 - NAT");
        content.add(viewed[10] + "3.11 - ARP");
        content.add(viewed[11] + "3.12 - ICMP");
        ListView lesson3List = (ListView) findViewById(R.id.lesson3ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, content);
        lesson3List.setAdapter(adapter);
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