package net.xylphid.betclic;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
                //action
                return true;
            case R.id.menuSignout:
                //action
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
