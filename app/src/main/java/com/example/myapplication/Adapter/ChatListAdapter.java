package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.Chat;

import java.util.List;

public class ChatListAdapter extends ArrayAdapter<Chat> {
    private int resourceLayout;
    private Context mContext;

    public ChatListAdapter(Context context, int resource, List<Chat> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }
    /*
getView() method. This view is called when a listItem needs to be created and populated with the data.In this method first the View is inflated using the LayoutInflator.inflate() method. It is important that you check that if the view you are trying to inflate is new or reused
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v == null)
            v = LayoutInflater.from(mContext).inflate(resourceLayout,parent,false);


        Chat p = getItem(position);

        if (p != null) {
            TextView chatName = (TextView) v.findViewById(R.id.chat_name);
            chatName.setText(p.getName());
            TextView chatDate=(TextView)  v.findViewById(R.id.chat_date);
            chatDate.setText(p.getDate());
        }



        return v;
    }


}
