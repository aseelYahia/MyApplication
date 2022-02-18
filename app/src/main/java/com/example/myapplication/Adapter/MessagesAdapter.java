package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Message;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MessagesAdapter extends ArrayAdapter<Message> {
    private Context context;
    private int resource;


    public MessagesAdapter(@NonNull Context context, int resource, ArrayList<Message> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        }
        // Code goes here
        Message p = getItem(position);

        if (p != null) {
            TextView chatName = (TextView) convertView.findViewById(R.id.text_view_username);
            chatName.setText(p.getSender().getName());
            TextView chatDate=(TextView)  convertView.findViewById(R.id.text_view_message);
            chatDate.setText(p.getText());
        }

        return convertView;
    }

}


