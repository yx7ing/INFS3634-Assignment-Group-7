package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
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

public class Lesson2_2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson2_2";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Update the database to show this lesson has been viewed
        Log.e(TAG, TAG + " view status is " + db.getLesson(202).getViewed());
        db.updateLesson(new DBLesson(202, 1));
        Log.e(TAG, TAG + " view status is " + db.getLesson(202).getViewed());

        // If all sub-lessons have been viewed
        // AND the trophy for the lesson has not yet been earned
        // reward it to the user and show a toast
        if (db.getTrophy(12).getEarned() == 0
                && db.getLesson(201).getViewed() == 1
                && db.getLesson(202).getViewed() == 1
                && db.getLesson(203).getViewed() == 1
                && db.getLesson(204).getViewed() == 1
                && db.getLesson(205).getViewed() == 1
                && db.getLesson(206).getViewed() == 1
                && db.getLesson(207).getViewed() == 1) {
            db.updateTrophy(new DBTrophy(12, 1));
            Toast.makeText(this, "You got a trophy for completing Lesson 2!", Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "Trophy 2 status is " + db.getTrophy(12).getEarned());

        setContentView(R.layout.activity_lesson2_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("INFS3617 Study Helper");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);

        TextView textView = findViewById(R.id.lesson2_2TextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
        final ListView listView = findViewById(R.id.lesson2_2ListView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=Transmission%20medium&exlimit=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        Pattern pattern = Pattern.compile("<p>(.*?)</p>|<ol>(.*?)</ol>|<ul>(?!<li>V)(.*?)</ul>|<span id=\\\\\"((?!Examples|Comparison_with_TCP.2FIP_model|See_also|References|External_links).*?)\\\\\">");
                        Matcher matcher = pattern.matcher(response);
                        ArrayList<String> content = new ArrayList<>();
                        content.add("\nGuided and Unguided Media");
                        while (matcher.find()){
                            String s = matcher.group();
                            s = s.replaceAll("</li>\\\\n<li>", "\n");
                            s = s.replaceAll("\\\\u00e9", "e");
                            s = s.replaceAll("\\\\u2013|\\\\u2014", " - ");
                            s = s.replaceAll("_|\\\\n", " ");
                            s = s.replaceAll("\\\\\"", "\"");
                            s = s.replaceAll("<p>|</p>|<b>|</b>|<i>|</i>|<ul>|</ul>|<li>|</li>|<ol>|</ol>|\\\"|>", "");
                            content.add(s);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Lesson2_2.this,android.R.layout.simple_list_item_1,content){
                            @Override
                            public View getView(int position, View convertView, ViewGroup parent){
                                TextView item = (TextView) super.getView(position,convertView,parent);
                                String itemText = super.getItem(position);
                                if (itemText.matches("<span id=.+") || position == 0){
                                    item.setTypeface(item.getTypeface(), Typeface.BOLD_ITALIC);
                                    item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
                                    item.setText(itemText.replaceAll("<span id=", "\n"));
                                } else {
                                    item.setTypeface(null,Typeface.NORMAL);
                                    item.setTextSize(14);
                                }
                                return item;
                            }
                        };
                        listView.setAdapter(arrayAdapter);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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