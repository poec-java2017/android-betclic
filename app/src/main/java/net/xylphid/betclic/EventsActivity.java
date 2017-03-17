package net.xylphid.betclic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import net.xylphid.betclic.adapter.EventAdapter;

import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_events);


        //Read securePreference
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String publicKey = sharedPref.getString(getString(R.string.secure_public), null);
        String privateKey = sharedPref.getString(getString(R.string.secure_private), null);
        Log.d( "Event", String.format("%s %s", publicKey,privateKey) );


        List<Event> eventList = new ArrayList<>();

        // TODO linker l'API
        Event event1 = new Event();
        event1.date = "25/02/2016";
        event1.title = "Football/Ligue 1/PSG - Bordeaux";
        eventList.add(event1);

        Event event2 = new Event();
        event2.date = "12/05/2016";
        event2.title = "Football/Ligue 1/OL - VAFC";
        eventList.add(event2);

        Event event3 = new Event();
        event3.date = "17/05/2016";
        event3.title = "Football/Ligue 1/Bordeaux - OL";
        eventList.add(event3);

        RecyclerView listContainer = (RecyclerView) findViewById(R.id.events_container);
        listContainer.setLayoutManager(new LinearLayoutManager(this));
        listContainer.setAdapter(new EventAdapter(eventList));
    }
}
