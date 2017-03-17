package net.xylphid.betclic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import net.xylphid.betclic.adapter.BetAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tbHeader);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }


        //Read securePreference
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String publicKey = sharedPref.getString(getString(R.string.secure_public), null);
        String privateKey = sharedPref.getString(getString(R.string.secure_private), null);
        Log.d( "Event", String.format("%s %s", publicKey,privateKey) );

        List<String> betList = new ArrayList<>();
        betList.add("BetChoice - QuoteChoice / Pseudo : SumPlayed");
        betList.add("BetChoice - QuoteChoice / Pseudo : SumPlayed");
        betList.add("BetChoice - QuoteChoice / Pseudo : SumPlayed");
        betList.add("BetChoice - QuoteChoice / Pseudo : SumPlayed");
        betList.add("BetChoice - QuoteChoice / Pseudo : SumPlayed");

        RecyclerView rvBetList = (RecyclerView) findViewById(R.id.rvBetList);
        rvBetList.setLayoutManager(new LinearLayoutManager(this));
        rvBetList.setAdapter(new BetAdapter(betList));
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
                //action
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
