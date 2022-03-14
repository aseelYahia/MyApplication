package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MusicService;
import com.example.myapplication.R;
import com.example.myapplication.Song;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    private Context context;
    private int resource;


    public SongAdapter(@NonNull Context context, int resource, ArrayList<Song> objects) {
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
        Song p = getItem(position);

        if (p != null) {
            TextView tv_song = (TextView) convertView.findViewById(R.id.song_name);
            tv_song.setText(p.getName());
            TextView tv_album = (TextView) convertView.findViewById(R.id.album_name);
            tv_album.setText(p.getAlbum());

            TextView btEdit = convertView.findViewById(R.id.play_song);
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("Music","item clicked pos "+position);
                    context.stopService(MainActivity.musicIntent);
                    MainActivity.record_idx=position;
                    MainActivity.musicIntent =new Intent(context, MusicService.class);
                    context.startService(MainActivity.musicIntent);


                }
            });



        }

        return convertView;
    }

}
