package net.xylphid.betclic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class BetAdapter extends RecyclerView.Adapter<BetAdapter.ViewHolder> {

    private List<String> betOngoing;

    public BetAdapter (List<String> betOngoing) {
        this.betOngoing = betOngoing;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_bet_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(String.format(Locale.getDefault(), "%s", betOngoing.get(position)));
    }

    @Override
    public int getItemCount() {
        return betOngoing.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.tvBetOngoing);
        }
    }
}
