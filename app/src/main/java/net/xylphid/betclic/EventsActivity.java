package net.xylphid.betclic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.securepreferences.SecurePreferences;

import net.xylphid.betclic.adapter.EventAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventsActivity extends AppCompatActivity {
    private List<Event> eventList = new ArrayList<>();
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbHeader);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }


        //Read securePreference
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String publicKey = sharedPref.getString(getString(R.string.secure_public), null);
        String privateKey = sharedPref.getString(getString(R.string.secure_private), null);
        Log.d("Event", String.format("%s %s", publicKey, privateKey));

        // TODO linker l'API

        Event event1 = new Event();
        event1.date = "25/02/2016";
        event1.id = "event1";
        event1.title = "Football/Ligue 1/PSG - Bordeaux";
        eventList.add(event1);


        RecyclerView listContainer = (RecyclerView) findViewById(R.id.events_container);
        listContainer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(eventList);
        listContainer.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_ongoing:
                                displayOngoingEvents();
                                break;
                            case R.id.action_finished:
                                displayFinishedEvents();
                        }
                        return true;
                    }
                }
        );
    }

    public void displayOngoingEvents() {
        eventList.clear();

        Event event1 = new Event();
        event1.date = "25/02/2016";
        event1.id = "event1";
        event1.title = "Football/Ligue 1/PSG - Bordeaux";
        eventList.add(event1);

        Event event2 = new Event();
        event2.date = "12/05/2016";
        event2.id = "event2";
        event2.title = "Football/Ligue 1/OL - VAFC";
        eventList.add(event2);

        adapter.setEvents(eventList);
    }

    public void displayFinishedEvents() {
        eventList.clear();

        Event event1 = new Event();
        event1.date = "25/02/2016";
        event1.id = "event1";
        event1.title = "Football/Ligue 1/PSG - Bordeaux";
        eventList.add(event1);

        Event event3 = new Event();
        event3.date = "17/05/2016";
        event3.id = "event3";
        event3.title = "Football/Ligue 1/Bordeaux - OL";
        eventList.add(event3);

        adapter.setEvents(eventList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.menuSignout:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        SharedPreferences sharedPref = new SecurePreferences(EventsActivity.this, "betclic", getString(R.string.preference_file_key));
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.secure_public), "no-connexion");
        editor.putString(getString(R.string.secure_private), "no-connexion");
        editor.commit();
        startActivity(new Intent(this, LandingActivity.class));
    }
}
