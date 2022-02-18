package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.Album;
import com.example.myapplication.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Album> {
    private int resourceLayout;
    private Context mContext;

    public CustomAdapter(Context context, int resource, List<Album> items) {
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


        Album p = getItem(position);

        if (p != null) {
            TextView tvName = (TextView) v.findViewById(R.id.albumName);
            tvName.setText(p.getName());
            TextView tvDate = (TextView) v.findViewById(R.id.albumrelease);
            tvDate.setText(p.getDate());

            TextView btEdit = v.findViewById(R.id.showSongs);
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            TextView btDelete = v.findViewById(R.id.showSongs);
            btDelete.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }

        return v;
    }




}
