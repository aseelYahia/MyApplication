package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context mContext;
    private List<Chat> mChats;

    public ChatAdapter(Context mContext, List<Chat> mChats)
    {
        this.mChats=mChats;
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.chat_item,parent,false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat=mChats.get(position);
        holder.username.setText(chat.getName());

    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profile_image;

        public ViewHolder (View itemView){
            super(itemView);
            this.username=username;
            this.profile_image=profile_image;
        }
    }
}