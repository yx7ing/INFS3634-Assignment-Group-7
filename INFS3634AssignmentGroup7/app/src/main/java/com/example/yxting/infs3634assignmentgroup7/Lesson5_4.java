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

public class Lesson5_4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson5_4";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Update the database to show this lesson has been viewed
        Log.e(TAG, TAG + " view status is " + db.getLesson(504).getViewed());
        db.updateLesson(new DBLesson(504, 1));
        Log.e(TAG, TAG + " view status is " + db.getLesson(504).getViewed());

        // If all sub-lessons have been viewed
        // AND the trophy for the lesson has not yet been earned
        // reward it to the user and show a toast
        if (db.getTrophy(15).getEarned() == 0
                && db.getLesson(501).getViewed() == 1
                && db.getLesson(502).getViewed() == 1
                && db.getLesson(503).getViewed() == 1
                && db.getLesson(504).getViewed() == 1
                && db.getLesson(505).getViewed() == 1
                && db.getLesson(506).getViewed() == 1
                && db.getLesson(507).getViewed() == 1) {
            db.updateTrophy(new DBTrophy(15, 1));
            Toast.makeText(this, "You got a trophy for completing Lesson 5!", Toast.LENGTH_LONG).show();
        }
        Log.e(TAG, "Trophy 5 status is " + db.getTrophy(15).getEarned());

        setContentView(R.layout.activity_lesson5_4);
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

        TextView textView = findViewById(R.id.lesson5_4TextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
        final ListView listView = findViewById(R.id.lesson5_4ListView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=Hypertext_Transfer_Protocol&exlimit=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.substring(0, 25351);
                        Pattern pattern = Pattern.compile("<p>(.*?)</p>|<ol>(.*?)</ol>|<ul>(.*?)</ul>|<span id=\\\\\"((?!Examples|See_also|References|External_links).*?)\\\\\">");
                        Matcher matcher = pattern.matcher(response);
                        ArrayList<String> content = new ArrayList<>();
                        content.add("\nHTTP");
                        while (matcher.find()){
                            String s = matcher.group();
                            s = s.replaceAll("</li>\\\\n<li>", "\n");
                            s = s.replaceAll("\\\\u00e9", "e");
                            s = s.replaceAll("\\\\u2013|\\\\u2014", " - ");
                            s = s.replaceAll("_|\\\\n|\\\\u00a0", " ");
                            s = s.replaceAll("\\\\\"", "\"");
                            s = s.replaceAll("<link.*?46", "");
                            s = s.replaceAll("<p>|</p>|<b>|</b>|<i>|</i>|<ul>|</ul>|<li>|</li>|<ol>|</ol>|\\\"|>", "");
                            s = s.replaceAll("&lt;", "<");
                            s = s.replaceAll("&gt;", ">");
                            content.add(s);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Lesson5_4.this,android.R.layout.simple_list_item_1,content){
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