package com.example.yxting.infs3634assignmentgroup7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Home";

    // Instantiate database
    DBHelper db = new DBHelper(this);

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On the first time the app is run, insert default database values
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        Log.e(TAG, String.valueOf(isFirstRun));
        if (isFirstRun)
        {
            addLessons(db);
            addTrophies(db);
            addQuestions(db);
            addQuizzes(db);
            addReviews(db);
            addPoints(db);

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }

        setContentView(R.layout.activity_home);
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
        navigationView.getMenu().getItem(0).setChecked(true);

        // Set and format static textviews
        TextView title = (TextView) findViewById(R.id.homeTextView);
        title.setText("Home");
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);

        TextView welcome = (TextView) findViewById(R.id.homeTextView2);
        welcome.setText("Welcome to the INFS3617 Study Helper!");
        welcome.setTextColor(Color.parseColor("#FFFFFF"));
        welcome.setTypeface(welcome.getTypeface(), Typeface.BOLD);
        welcome.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);

        TextView points = (TextView) findViewById(R.id.homeTextView4);
        points.setText("Points");
        points.setTextColor(Color.parseColor("#FFFFFF"));
        points.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView medals = (TextView) findViewById(R.id.homeTextView6);
        medals.setText("Medals");
        medals.setTextColor(Color.parseColor("#FFFFFF"));
        medals.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        // Set and format dynamic textviews
        TextView lessons = (TextView) findViewById(R.id.homeTextView3);
        int lessonsViewed = 0;
        for (int i = 0; i < 6; i++) {
            if (db.getLesson(101+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(201+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (db.getLesson(301+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (db.getLesson(401+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(501+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        int quizzesCompleted = 0;
        for (int i = 0; i < 5; i++) {
            if (db.getQuiz(1+i).getCompleted() == 1) {
                quizzesCompleted++;
            }
        }
        lessons.setText(lessonsViewed + " of 41 lessons viewed\n" + quizzesCompleted + " of 5 quizzes completed");
        lessons.setTextColor(Color.parseColor("#FFFFFF"));
        lessons.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView pointsValue = (TextView) findViewById(R.id.homeTextView5);
        pointsValue.setText(Integer.toString(db.getPoints(1).getPoints()));
        pointsValue.setTextColor(Color.parseColor("#FFFFFF"));
        pointsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView medalsValue = (TextView) findViewById(R.id.homeTextView7);
        medalsValue.setText(Integer.toString(db.getPoints(1).getPoints()/1000));
        medalsValue.setTextColor(Color.parseColor("#FFFFFF"));
        medalsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView trophies = (TextView) findViewById(R.id.homeTextView8);
        int trophiesEarned = 0;
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(11+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(21+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(31+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        trophies.setText(trophiesEarned + " of 15 trophies earned");
        trophies.setTextColor(Color.parseColor("#FFFFFF"));
        trophies.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        // Set selected item in navigation drawer on restart (for when user presses back to return to this activity)
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        // Same as on create, update textviews when pressing back to get to this activity
        TextView lessons = (TextView) findViewById(R.id.homeTextView3);
        int lessonsViewed = 0;
        for (int i = 0; i < 6; i++) {
            if (db.getLesson(101+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(201+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 12; i++) {
            if (db.getLesson(301+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (db.getLesson(401+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        for (int i = 0; i < 7; i++) {
            if (db.getLesson(501+i).getViewed() == 1) {
                lessonsViewed++;
            }
        }
        int quizzesCompleted = 0;
        for (int i = 0; i < 5; i++) {
            if (db.getQuiz(1+i).getCompleted() == 1) {
                quizzesCompleted++;
            }
        }
        lessons.setText(lessonsViewed + " of 41 lessons viewed\n" + quizzesCompleted + " of 5 quizzes completed");
        lessons.setTextColor(Color.parseColor("#FFFFFF"));
        lessons.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView pointsValue = (TextView) findViewById(R.id.homeTextView5);
        pointsValue.setText(Integer.toString(db.getPoints(1).getPoints()));
        pointsValue.setTextColor(Color.parseColor("#FFFFFF"));
        pointsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView medalsValue = (TextView) findViewById(R.id.homeTextView7);
        medalsValue.setText(Integer.toString(db.getPoints(1).getPoints()/1000));
        medalsValue.setTextColor(Color.parseColor("#FFFFFF"));
        medalsValue.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        TextView trophies = (TextView) findViewById(R.id.homeTextView8);
        int trophiesEarned = 0;
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(11+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(21+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (db.getTrophy(31+i).getEarned() == 1) {
                trophiesEarned++;
            }
        }
        trophies.setText(trophiesEarned + " of 15 trophies earned");
        trophies.setTextColor(Color.parseColor("#FFFFFF"));
        trophies.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
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
            // Current page
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

    // A record for each sub-lesson; 101 - Lesson1_1, 102 - Lesson1_2, etc.
    private void addLessons(DBHelper db){
        db.addLesson(new DBLesson(101, 0));
        db.addLesson(new DBLesson(102, 0));
        db.addLesson(new DBLesson(103, 0));
        db.addLesson(new DBLesson(104, 0));
        db.addLesson(new DBLesson(105, 0));
        db.addLesson(new DBLesson(106, 0));

        db.addLesson(new DBLesson(201, 0));
        db.addLesson(new DBLesson(202, 0));
        db.addLesson(new DBLesson(203, 0));
        db.addLesson(new DBLesson(204, 0));
        db.addLesson(new DBLesson(205, 0));
        db.addLesson(new DBLesson(206, 0));
        db.addLesson(new DBLesson(207, 0));

        db.addLesson(new DBLesson(301, 0));
        db.addLesson(new DBLesson(302, 0));
        db.addLesson(new DBLesson(303, 0));
        db.addLesson(new DBLesson(304, 0));
        db.addLesson(new DBLesson(305, 0));
        db.addLesson(new DBLesson(306, 0));
        db.addLesson(new DBLesson(307, 0));
        db.addLesson(new DBLesson(308, 0));
        db.addLesson(new DBLesson(309, 0));
        db.addLesson(new DBLesson(310, 0));
        db.addLesson(new DBLesson(311, 0));
        db.addLesson(new DBLesson(312, 0));

        db.addLesson(new DBLesson(401, 0));
        db.addLesson(new DBLesson(402, 0));
        db.addLesson(new DBLesson(403, 0));
        db.addLesson(new DBLesson(404, 0));
        db.addLesson(new DBLesson(405, 0));
        db.addLesson(new DBLesson(406, 0));
        db.addLesson(new DBLesson(407, 0));
        db.addLesson(new DBLesson(408, 0));
        db.addLesson(new DBLesson(409, 0));

        db.addLesson(new DBLesson(501, 0));
        db.addLesson(new DBLesson(502, 0));
        db.addLesson(new DBLesson(503, 0));
        db.addLesson(new DBLesson(504, 0));
        db.addLesson(new DBLesson(505, 0));
        db.addLesson(new DBLesson(506, 0));
        db.addLesson(new DBLesson(507, 0));
    }

    private void addTrophies(DBHelper db){
        // 5 bronze trophies, each for completing a Lesson
        db.addTrophy(new DBTrophy(11, 0));
        db.addTrophy(new DBTrophy(12, 0));
        db.addTrophy(new DBTrophy(13, 0));
        db.addTrophy(new DBTrophy(14, 0));
        db.addTrophy(new DBTrophy(15, 0));

        // 5 silver trophies each for completing a Lesson Quiz
        db.addTrophy(new DBTrophy(21, 0));
        db.addTrophy(new DBTrophy(22, 0));
        db.addTrophy(new DBTrophy(23, 0));
        db.addTrophy(new DBTrophy(24, 0));
        db.addTrophy(new DBTrophy(25, 0));

        // 5 gold trophies for getting full marks in a Lesson Quiz
        db.addTrophy(new DBTrophy(31, 0));
        db.addTrophy(new DBTrophy(32, 0));
        db.addTrophy(new DBTrophy(33, 0));
        db.addTrophy(new DBTrophy(34, 0));
        db.addTrophy(new DBTrophy(35, 0));
    }

    // Add 12 questions for each of 5 lessons
    private void addQuestions(DBHelper db){
        db.addQuestion(new DBQuestion(1, "Which of the following is NOT true about networking?", "a. Hardware is included as a component", "b. Networking does not include software", "c. Protocols are necessary in networking", "d. Networking includes the maintenance of the computer networks", 3, "1.1 - What is Networking?"));
        db.addQuestion(new DBQuestion(2, "Which is the 5th Layer of the OSI Model?", "a. Data Link", "b. Transport", "c. Application", "d. Session", 4, "1.2 Introduction to OSI Model"));
        db.addQuestion(new DBQuestion(3, "What is the basic function of the network layer?", "a. To perform IP addressing and routing", "b. To perform error detection", "c. To provide reliable transfer of data between end points", "d. To format data for presentation", 1, "1.2 Introduction to OSI Model"));
        db.addQuestion(new DBQuestion(4, "What is TRUE in a client-server model?", "a. Nodes pool resources", "b. One node serves all the other nodes on the same system", "c. If a resource becomes unavailable, the rest of the nodes still offer the same resources", "d. The nodes communication with each other directly", 2, "1.3 - Networking Architecture- Client Server Model"));
        db.addQuestion(new DBQuestion(5, "What are the two sublayers of the data link layer?", "a. Medium Access Control and Logical Link Control Layer", "b. Most Access Control and Logical Link Control Layer", "c. Most Access Centre and Least Link Control Layer", "d. Medium Access Control and Logical List Control Layer", 1, "1.2 - Introduction to OSI Model"));
        db.addQuestion(new DBQuestion(6, "What are the disadvantages of the analog transmission method, as compared to the digital transmission method?", "a. Higher costs", "b. High signal-to-noise ratio", "c. More complex equipment necessary", "d. Attenuation is better", 2, "1.5 - Analog Transmission"));
        db.addQuestion(new DBQuestion(7, "Which one of these is not a network topology?", "a. Star", "b. Ring", "c. Bus", "d. Square", 4, "1.6 - Data Transmission"));
        db.addQuestion(new DBQuestion(8, "Why may peer to peer networks be more vulnerable to remote exploits than client server networks?", "a. The many nodes increase entry points into the system", "b. They can be corrupted more easily", "c. They act as both clients and servers", "d. They are not more vulnerable than client server networks", 3, "1.4 - Peer to Peer Network"));
        db.addQuestion(new DBQuestion(9, "Which of the following means is used to convey analog transmissions?", "a. Fiber optic cable", "b. Air", "c. Water", "d. All of the above", 4, "1.5 - Analog Transmission"));
        db.addQuestion(new DBQuestion(10, "In what way is data represented when being transmitted?", "a. Electric", "b. Sound", "c. Electromagnetic", "d. Mechanical", 3, "1.6 - Data Transmission"));
        db.addQuestion(new DBQuestion(11, "What are the differences between client server and peer to peer networks?", "a. All nodes are equal", "b. Peer to peer networks share files", "c. Both A and B", "d. None of the above", 3, "1.4 - Peer to Peer Network"));
        db.addQuestion(new DBQuestion(12, "Which of the following are NOT cross layer functions?", "a. Domain Name Server (DNS)","b. Logical addressing", "c. DHCP", "d. ARP", 2, "1.2 - Introduction to OSI Model"));
        db.addQuestion(new DBQuestion(13, "The physical layer of the OSI model requires which elements?", "a. Encoding of data and control information", "b. A representation of bits on the media", "c. The physical media and associated connectors", "d. All of the above", 4, "2.1 - OSI Physical Layer"));
        db.addQuestion(new DBQuestion(14, "Which of these is not a characteristic of a twisted pair?", "a. Separately insulated", "b.  Twisted together", "c. Carries equal and same signals", "d. Often \"bundled\" into cables", 3, "2.3 - Shielded and Unshielded Twisted Pair"));
        db.addQuestion(new DBQuestion(15, "Which of these are examples of unguided transmission?", "a. Radio transmission", "b. Microwave", "c. Infra-red", "d. All of the above", 4, "2.2 - Guided and Unguided Media"));
        db.addQuestion(new DBQuestion(16, "Which is NOT an advantage of the coaxial cable?", "a. Less expensive", "b. Less susceptible to interference and cross talk", "c. Good immunity from electromagnetic interference", "d. Can be used over longer distances", 1, "2.4 - Coaxial Cable "));
        db.addQuestion(new DBQuestion(17, "What reduces the strength of a fiber optic cable?", "a. Use of plastic fibers ", "b. Microscopic surface flaws", "c. Use of glass fibers", "d. Many fibers used in a single cable", 2, "2.5 - Optical Fiber Cable"));
        db.addQuestion(new DBQuestion(18, "What are the two levels of network topologies?", "a. Physical and Logical", "b. Lower and Upper", "c. Local and Wide", "d. None of the above", 1, "2.6 - Network Topologies"));
        db.addQuestion(new DBQuestion(19, "What is framing?", "a. When some bits are removed from the stream", "b. When specific bits are added to the middle of a stream of bits", "c. When sequences are added at the beginning and end of a stream of bits", "d. None of the above", 3, "2.7 - Data Link Layer - Media Access Control Layer and Logical Link Control Layer"));
        db.addQuestion(new DBQuestion(20, "What is NOT true for the Logical Link Control Layer?", "a. It is a connecting layer between the software and physical", "b. It identifies which protocol layer is being used", "c. It encapsulated data", "d. It regulates the placement of data frame onto the transmission medium", 4, "2.7 - Data Link Layer - Media Access Control Layer and Logical Link Control Layer"));
        db.addQuestion(new DBQuestion(21, "What is a disadvantage of the mesh topology?", "a. Great redundancy", "b. High cost", "c. Both A and B", "d. None of the above", 3, "2.6 - Network Topologies"));
        db.addQuestion(new DBQuestion(22, "Twisted pairs often incorporate ______ to prevent electromagnetic interference? Fill the gap.", "a. Multiple cables", "b. Shielding", "c. Carrying opposite signals", "d. Higher twist rates", 3, "2.3 - Shielded and Unshielded Twisted Pair"));
        db.addQuestion(new DBQuestion(23, "Which of these are used for guided transmission?", "a. Shielded Twisted Pair (STP)", "b. Unshielded Twisted Pair (UTP)", "c. Coaxial Cable", "d. All of the above", 4, "2.2 - Guided and Unguided Media"));
        db.addQuestion(new DBQuestion(24, "Which type of cable is used in cable TV?", "a. Broadband", "b. Baseband", "c. Highband", "d. Lowband", 1, "2.4 - Coaxial Cable"));
        db.addQuestion(new DBQuestion(25, "Which device connects multiple network segments and works on layers 1 and 2 of the OSI model?", "a. Switch", "b. Bridge", "c. Repeater", "d. Hub", 2, "3.1 - Hubs, Routers, Switches and Bridges"));
        db.addQuestion(new DBQuestion(26, "When does a collision domain particularly apply?", "a. Wireless connections", "b. Wired connections", "c. Later versions of Ethernet", "d. None of the above", 1, "3.2 - Collision Domains"));
        db.addQuestion(new DBQuestion(27, "Which statement is false?", "a. A broadcast domain has many nodes which can reach each other through broadcast", "b. Can be within the same LAN or can be bridged to other LAN segments", "c. A router is a boundary between broadcast domains", "d. None of the above", 4, "3.3 - Broadcast Domains"));
        db.addQuestion(new DBQuestion(28, "Which of these is NOT a function of the network layer?", "a. Connectionless communication", "b. Host addressing ", "c. Authorisation", "d. Message forwarding", 3, "3.4 - Network Layer"));
        db.addQuestion(new DBQuestion(29, "What is a logical address?", "a. Binary number on the address bus circuitry", "b. 32 bit IP address not embedded in network card", "c. Physical location of the device", "d. The address of where the device was bought", 2, "3.5 - Logical Addressing"));
        db.addQuestion(new DBQuestion(30, "How many classes does IPv4 have?", "a. 5", "b. 4", "c. 3", "d. 6", 1, "3.6 - IPv4"));
        db.addQuestion(new DBQuestion(31, "IPv6 uses a __ bit address. Fill the gap.", "a. 32", "b. 16", "c. 128", "d. 256", 3, "3.7 - IPv6"));
        db.addQuestion(new DBQuestion(32, "What is a subnet mask?", "a. 255 or 0", "b. A sting of numbers in any order", "c. A string of zeros followed by ones", "d. String of ones followed by zeros", 4, "3.8 - Subnetting"));
        db.addQuestion(new DBQuestion(33, "What is the correct order of the DHCP operation?", "a. Acknowledge, discovery, offer request", "b. Discovery, offer, request, acknowledge", "c. Request, offer, acknowledge, discovery", "d. Offer, discovery, request, acknowledge", 2, "3.9 - DHCP"));
        db.addQuestion(new DBQuestion(34, "Why is NAT needed?", "a. To translate global IP addresses to private network addresses", "b. To translate private network addresses into global IP addresses", "c. To assign IP addresses to devices", "d. None of the above", 2, "3.10 - NAT"));
        db.addQuestion(new DBQuestion(35, "What does ARP do?", "a. Pings the host device", "b. Finds to IP address of a host", "c. Translates private network addresses to global IP addresses", "d. Finds the MAC address of a device", 4, "3.11 - ARP"));
        db.addQuestion(new DBQuestion(36, "What command is used by ICMP?", "a. ipconfig", "b. telnet", "c. ping", "d. tracert", 3, "3.12 - ICMP"));
        db.addQuestion(new DBQuestion(37, "What is NOT an advantage of subnetting?", "a. Enhance routing efficiency", "b. Better network management", "c. Easy to design and manage", "d. Allocating network space efficiently", 3, "4.1 - Subnetting"));
        db.addQuestion(new DBQuestion(38, "What is the basic function of routing?", "a. Determining a route for sending a packet from one host to another", "b. Finding the IP address of destination of the packets", "c. Finding the MAC address of the destination of the packets", "d. Connecting a device to the Internet", 1, "4.2 - Routing"));
        db.addQuestion(new DBQuestion(39, "When is routing needed?", "a. Every time a packet is sent to a different host", "b. When hosts have IP addresses with different network IDs", "c. When hosts have different IP addresses", "d. None of the above", 2, "4.2 - Routing"));
        db.addQuestion(new DBQuestion(40, "Which is not a use of static routing?", "a. Define an exit point from a router when no routes are available or necessary", "b. Small networks that only require one or two routers", "c. Backup for dynamic routing", "d. None of the above", 4, "4.3 - Fixed Routing"));
        db.addQuestion(new DBQuestion(41, "Which of these is an advantage of static routing?", "a. Less errors in routing", "b. Less load on the CPU of the router", "c. It is fault tolerant", "d. Easy and quick to set up with minimal overhead", 2, "4.3 - Fixed Routing"));
        db.addQuestion(new DBQuestion(42, "Which of these is NOT a protocol used in dynamic routing?", "a. Least Hops Route (LHR)", "b. Routing Information Protocol (RIP)", "c. Open Shortest Path First (OSPF)", "d. Link State Routing (LSR)", 1, "4.4 - Dynamic Routing"));
        db.addQuestion(new DBQuestion(43, "On what basis does the Distance-Vector Routing Protocol operate?", "a. Least hops", "b. Most hops", "c. Route with least traffic", "d. Route with most traffic", 1, "4.5 - Distance Vector Protocol"));
        db.addQuestion(new DBQuestion(44, "What is NOT true for the Link State Protocol?", "a. Each router identifies all other devices on the connected networks", "b. Each router does not share information", "c. Each router advertises a list of all directly connected network links and the associated cost of each link ", "d. Link state routing protocols do not exchange routing tables", 2, "4.6 - Link State Protocol"));
        db.addQuestion(new DBQuestion(45, "Which two types of messages does Routing Information Protocol work with?", "a. Offer and discovery", "b. Request and acknowledge", "c. Request and response", "d. Syn and syn-ack", 3, "4.7 - RIP"));
        db.addQuestion(new DBQuestion(46, "Which functions does RIPv2 NOT support?", "a. Variable length subnet masking", "b. Larger network implementations", "c. Multicasting", "d. Compatibility switch function", 2, "4.7 - RIP"));
        db.addQuestion(new DBQuestion(47, "On which basis does OSPF operate?", "a. Number of hops and error rates and traffic", "b. Only number of hops", "c. Only error rates and traffic", "d. None of the above", 1, "4.8 - OSPF"));
        db.addQuestion(new DBQuestion(48, "Four statements are listed below. Which is the correct order of the DNS Resolution process?\nI. Resolver consults local name cache, and if the mapping is not in the cache, it formulates a query to the name server.\nII. Local resolver is initiated.\nIII. Web browser issues a request to ask for IP address of a host by passing host name.\nIV. Name server checks if the answer is in local database or cache – otherwise it queries other available name servers.", "a. III, IV, II, I", "b. I, III, II, IV", "c. I, IV, III, II", "d. III, II, I, IV ", 3, "4.9 - DNS"));
        db.addQuestion(new DBQuestion(49, "Which two protocols is the TCP/IP Protocol Suite names after?", "a. Transaction Control Protocol and Internet Protocol", "b. Transmission Control Protocol and Internet Protocol", "c. Transmission Control Protocol and Intermediate Protocol", "d. Transaction Control Protocol and Intermediate Protocol", 2, "5.1 - TCP/IP Protocol Suite"));
        db.addQuestion(new DBQuestion(50, "What are the four layers in the TCP/IP model?", "a. Application, presentation, transport, physical", "b. Data Link, transport, session, link", "c. Data link, internet, transport, application", "d. Data link, physical, transport, presentation", 3, "5.1 - TCP/IP Protocol Suite"));
        db.addQuestion(new DBQuestion(51, "What is NOT true for TCP?", "a. TCP units are called segments", "b. TCP does not assume reliability from lower level protocols", "c. TCP operates in the application layer", "d. TCP connection must be established before units can be sent", 3, "5.2 - TCP"));
        db.addQuestion(new DBQuestion(52, "What is the correct order and sequence numbers for the three way handshake?", "a. SYN (SEQ = x), SYN-ACK (SEQ = y, ACK = x + 1), ACK (SEQ = x + 1, ACK = y + 1)", "b. SYN (SEQ = x), SYN-ACK (SEQ = y, ACK = x), ACK (SEQ = x + 1, ACK = y + 1)", "c. SYN-ACK (SEQ = y, ACK = x), SYN (SEQ = x), ACK (SEQ = x + 1, ACK = y + 1)", "d. SYN-ACK (SEQ = y, ACK = x + 1), SYN (SEQ = x), ACK (SEQ = x + 1, ACK = y + 1)", 1, "5.2 - TCP"));
        db.addQuestion(new DBQuestion(53, "What are data units for UDP called?", "a. Packets ", "b. Datagram", "c. Segments", "d. Bits", 2, "5.3 - UDP"));
        db.addQuestion(new DBQuestion(54, "Which of these is NOT a disadvantage of UDP as compared to TCP?", "a. Unreliable ", "b. Not ordered", "c. No congestion control", "d. Slower", 4, "5.3 - UDP"));
        db.addQuestion(new DBQuestion(55, "Which transport protocol does HTTP use?", "a. DNS ", "b. UDP", "c. TCP", "d. None of the above", 3, "5.4 - HTTP"));
        db.addQuestion(new DBQuestion(56, "In HTTP, the server response includes what information?", "a. Only protocol version", "b. Protocol version and success or error code", "c. Only success or error code", "d. Protocol version, success or error code, message containing server information and possible body content", 4, "5.4 - HTTP"));
        db.addQuestion(new DBQuestion(57, "Which port does SMTP use?", "a. 25", "b. 80", "c. 125", "d. 50", 1, "5.5 - SMPT"));
        db.addQuestion(new DBQuestion(58, "Which of the following statements is most correct?", "a. SMTP and POP generally used to send emails, IMAP generally used to retrieve emails", "b. SMTP generally used to send emails, POP and IMAP generally used to retrieve emails", "c. IMAP generally used to send emails, POP and SMTP generally used to retrieve emails", "d. POP, IMAP generally used to send email, SMTP generally used to retrieve emails", 2, "5.5 - SMPT"));
        db.addQuestion(new DBQuestion(59, "Which of these is NOT a disadvantage of telnet?", "a. Does not encrypt data", "b. No authentication", "c. Harder to implement", "d. Slower", 3, "5.6 - Telnet"));
        db.addQuestion(new DBQuestion(60, "Which of these is NOT true in relation to SSH?", "a. Offers greater protection when remotely accessing host over the network", "b. Allows information to be encrypted and compressed", "c. Public/private keys can be used to verify the user and the identity of the remote system", "d. Information once encrypted cannot be decrypted externally", 4, "5.7 - SSH"));
    }

    // Add 5 tracker records, one for each quiz
    private void addQuizzes(DBHelper db){
        db.addQuiz(new DBQuiz(1, 0, 0));
        db.addQuiz(new DBQuiz(2, 0, 0));
        db.addQuiz(new DBQuiz(3, 0, 0));
        db.addQuiz(new DBQuiz(4, 0, 0));
        db.addQuiz(new DBQuiz(5, 0, 0));
    }

    // Add a review entry corresponding to each question, to store the most recent response and whether it has been answered correctly before
    // See DBReview for reasoning
    private void addReviews(DBHelper db){
        db.addReview(new DBReview(1, 0, 0));
        db.addReview(new DBReview(2, 0, 0));
        db.addReview(new DBReview(3, 0, 0));
        db.addReview(new DBReview(4, 0, 0));
        db.addReview(new DBReview(5, 0, 0));
        db.addReview(new DBReview(6, 0, 0));
        db.addReview(new DBReview(7, 0, 0));
        db.addReview(new DBReview(8, 0, 0));
        db.addReview(new DBReview(9, 0, 0));
        db.addReview(new DBReview(10, 0, 0));
        db.addReview(new DBReview(11, 0, 0));
        db.addReview(new DBReview(12, 0, 0));
        db.addReview(new DBReview(13, 0, 0));
        db.addReview(new DBReview(14, 0, 0));
        db.addReview(new DBReview(15, 0, 0));
        db.addReview(new DBReview(16, 0, 0));
        db.addReview(new DBReview(17, 0, 0));
        db.addReview(new DBReview(18, 0, 0));
        db.addReview(new DBReview(19, 0, 0));
        db.addReview(new DBReview(20, 0, 0));
        db.addReview(new DBReview(21, 0, 0));
        db.addReview(new DBReview(22, 0, 0));
        db.addReview(new DBReview(23, 0, 0));
        db.addReview(new DBReview(24, 0, 0));
        db.addReview(new DBReview(25, 0, 0));
        db.addReview(new DBReview(26, 0, 0));
        db.addReview(new DBReview(27, 0, 0));
        db.addReview(new DBReview(28, 0, 0));
        db.addReview(new DBReview(29, 0, 0));
        db.addReview(new DBReview(30, 0, 0));
        db.addReview(new DBReview(31, 0, 0));
        db.addReview(new DBReview(32, 0, 0));
        db.addReview(new DBReview(33, 0, 0));
        db.addReview(new DBReview(34, 0, 0));
        db.addReview(new DBReview(35, 0, 0));
        db.addReview(new DBReview(36, 0, 0));
        db.addReview(new DBReview(37, 0, 0));
        db.addReview(new DBReview(38, 0, 0));
        db.addReview(new DBReview(39, 0, 0));
        db.addReview(new DBReview(40, 0, 0));
        db.addReview(new DBReview(41, 0, 0));
        db.addReview(new DBReview(42, 0, 0));
        db.addReview(new DBReview(43, 0, 0));
        db.addReview(new DBReview(44, 0, 0));
        db.addReview(new DBReview(45, 0, 0));
        db.addReview(new DBReview(46, 0, 0));
        db.addReview(new DBReview(47, 0, 0));
        db.addReview(new DBReview(48, 0, 0));
        db.addReview(new DBReview(49, 0, 0));
        db.addReview(new DBReview(50, 0, 0));
        db.addReview(new DBReview(51, 0, 0));
        db.addReview(new DBReview(52, 0, 0));
        db.addReview(new DBReview(53, 0, 0));
        db.addReview(new DBReview(54, 0, 0));
        db.addReview(new DBReview(55, 0, 0));
        db.addReview(new DBReview(56, 0, 0));
        db.addReview(new DBReview(57, 0, 0));
        db.addReview(new DBReview(58, 0, 0));
        db.addReview(new DBReview(59, 0, 0));
        db.addReview(new DBReview(60, 0, 0));
    }

    // Add the record for storing the player's points (and medals)
    private void addPoints(DBHelper db){
        db.addPoints(new DBPoints(1, 0));
    }
}