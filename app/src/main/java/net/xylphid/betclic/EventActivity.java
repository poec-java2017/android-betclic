package net.xylphid.betclic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import net.xylphid.betclic.adapter.BetAdapter;
import net.xylphid.betclic.utils.IntentUtils;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        String id = getIntent().getExtras().getString(IntentUtils.ID_EVENT_KEY);

        //Read securePreference
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String publicKey = sharedPref.getString(getString(R.string.secure_public), null);
        String privateKey = sharedPref.getString(getString(R.string.secure_private), null);
        Log.d( "Event", String.format("%s %s", publicKey,privateKey) );

        View doBet = findViewById(R.id.doBet);
        doBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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

    public void displayBet(View view) {
        startActivity(new Intent(this, BetActivity.class));
    }
}
