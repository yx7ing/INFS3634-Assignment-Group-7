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
import android.widget.AdapterView;
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

public class Lesson1_2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Lesson1_2";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Update the database to show this lesson has been viewed
        Log.e(TAG, TAG + " view status is " + db.getLesson(102).getViewed());
        db.updateLesson(new DBLesson(102, 1));
        Log.e(TAG, TAG + " view status is " + db.getLesson(102).getViewed());

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

        setContentView(R.layout.activity_lesson1_2);
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
        TextView textView = findViewById(R.id.lesson1_2TextView);
        textView.setTextColor(Color.parseColor("#FF9933"));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        // Using Volley to access internet and make API calls
        // Set url to wikipedia page, using mediawiki API to get content
        // Create string request to get API response as a string

        // The response string is in json format
        // The content of the wikipedia page is in one field of the json
        // still in raw HTML
        // Parse this string using regex to get the text between HTML tags
        // Paragraphs are between <p> </p>
        // Dot points are between <ul> </ul>
        // Headings are inside <span id="">

        // Create an arraylist of strings to store the parsed paragraphs/headings
        // Manually add the page title
        // Loop through the regex's match groups for each paragraph/heading
        // Use string method replaceAll to remove HTML tags, unicode, etc.
        // Add the cleaned string to the list
        // Add the list to an array adapter

        // Override the getView method to set custom formatting of rows
        // Check if the String begins with <span id=, meaning it is a heading
        // (or if position is 0, i.e. the manually added title)
        // Change its font size and typeface.
        // Remove the <span id= and replace it with a newline character
        // to space out paragraphs

        // In the else{}, this is a check for when the listview is scrolled through
        // which updates the rows. This section of code is necessary to ensure only
        // the initially formatted rows are bolded/enlarged and maintain default
        // formatting for other rows, i.e. resetting their typeface and font size.

        // Populate listview with adapter

        // The above steps with the parsing and populating the listview
        // must be done within the stringRequest thread, as this thread
        // runs in the background. We can only populate and format the
        // listview once we have a response, otherwise it will be empty.

        // Finally, add the string request to the queue and execute it.

        final ListView listView = findViewById(R.id.lesson1_2ListView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=OSI_model&exlimit=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, response);
                        Pattern pattern = Pattern.compile("<p>(.*?)</p>|<ul>(?!<li>Mi)(.*?)</ul>|<span id=\\\\\"((?!Examples|Comparison_with_TCP.2FIP_model|See_also|References|External_links).*?)\\\\\">");
                        Matcher matcher = pattern.matcher(response);
                        ArrayList<String> content = new ArrayList<>();
                        content.add("\nOSI Model");
                        while (matcher.find()){
                            String s = matcher.group();
                            s = s.replaceAll("</li>\\\\n<li>", "\n");
                            s = s.replaceAll("\\\\u00e9", "e");
                            s = s.replaceAll("\\\\u2013|\\\\u2014", " - ");
                            s = s.replaceAll("_|\\\\n", " ");
                            s = s.replaceAll("\\\\\"", "\"");
                            s = s.replaceAll("<p>|</p>|<b>|</b>|<i>|</i>|<ul>|</ul>|<li>|</li>|\\\"|>", "");
                            content.add(s);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Lesson1_2.this,android.R.layout.simple_list_item_1,content){
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
    protected void onRestart(){
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