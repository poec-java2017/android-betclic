package net.xylphid.betclic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

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
}
