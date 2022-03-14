package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.SongAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity implements DialogInterface.OnClickListener   {

    ArrayList<Song> items;
    SongAdapter adapter;

    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root=db.getReference("album");



    ArrayAdapter arrayAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        lvItems = findViewById(R.id.lvItems);

        items=new ArrayList<>();

        items.add(new Song("All too well","Red",0));
        items.add(new Song("Blank space","1989",1));
        items.add(new Song("Cardigan","folklore",2));
        items.add(new Song("Champagne problems","evermore",3));
        items.add(new Song("Clean","1989",4));
        items.add(new Song("Cornilia Street","Lover",5));
        items.add(new Song("Enchanted","Speak now",6));
        items.add(new Song("Hey Stephen","Fearless",7));
        items.add(new Song("New year's day","Reputation",8));
        items.add(new Song("The last time","Red",9));
        items.add(new Song("Time McGraw","Debut",10));
        items.add(new Song("22","1989",11));
        items.add(new Song("Wonderland","1989",12));

        adapter=new SongAdapter(this, R.layout.custom_album_row,items);
        lvItems.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){

            case R.id.itemMusic:
                intent=new Intent(this, MusicActivity.class);
                startActivity(intent);
                break;

            case R.id.itemProfile:
                intent=new Intent(this, ActivityChat.class);
                startActivity(intent);

                break;
            case R.id.itemWriteSong:
                intent=new Intent(this, WriteSongActivity.class);
                startActivity(intent);
                break;

            case R.id.itemcelebrate:
                intent=new Intent(this, BirthdayActivity.class);
                startActivity(intent);
                break;


            case R.id.itemLogout:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("are you sure?");
                builder.setCancelable(false);
                builder.setPositiveButton("yes",this);
                builder.setNegativeButton("No", this);
                AlertDialog dialog_logout=builder.create();
                dialog_logout.show();

                break;


        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionItemSelected

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent;
        if(which==dialog.BUTTON_POSITIVE){
            intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            dialog.cancel();
        }
        if(which==dialog.BUTTON_NEGATIVE){
            dialog.cancel();
        }

    }

}