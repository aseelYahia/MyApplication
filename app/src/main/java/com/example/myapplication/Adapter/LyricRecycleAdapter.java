package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Lyric;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LyricRecycleAdapter extends RecyclerView.Adapter<LyricRecycleAdapter.MyViewHolder>  {
    ArrayList<Lyric> cardLyrics;
    Context context;

    public LyricRecycleAdapter(Context context, ArrayList cardLyrics) {
        this.context = context;
        this.cardLyrics= cardLyrics;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyric_card_item, parent, false);
// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(cardLyrics.get(position).getTitle().toString());
        holder.text.setText(cardLyrics.get(position).getText().toString());

    }





    @Override
    public int getItemCount() {
        return cardLyrics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView title;
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            title = (TextView) itemView.findViewById(R.id.textview_titlelyrics);
            text= (TextView) itemView.findViewById(R.id.textview_thetext);

        }

    }

}
