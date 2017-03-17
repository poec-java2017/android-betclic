package net.xylphid.betclic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Event;
import net.xylphid.betclic.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> events;
    private Context context;

    public EventAdapter(List<Event> eventList){
        this.events = eventList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        //holder.ivImage.setSrc(events.get(position).sport);
        // TODO gérer la sélection de l'image correspondant au sport
        holder.tvTitle.setText(events.get(position).title);
        holder.tvDate.setText(events.get(position).date);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView ivImage;
        TextView tvTitle;
        TextView tvDate;

        ViewHolder(View view){
            super(view);
            //this.ivImage = (ImageView) view.findViewById(R.id.ivEventImage);
            this.tvTitle = (TextView) view.findViewById(R.id.tvEventTitle);
            this.tvDate = (TextView) view.findViewById(R.id.tvEventDate);
        }
    }
}
