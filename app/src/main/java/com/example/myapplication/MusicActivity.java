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

import com.example.myapplication.Adapter.CustomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity implements DialogInterface.OnClickListener   {

    ArrayList<Album> items;
    ArrayList<String> items2;
    CustomAdapter adapter;

    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root=db.getReference("album");



    ArrayAdapter arrayAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        lvItems = findViewById(R.id.lvItems);

        items = new ArrayList<>();
        //String[] red={"red","all too well"};
        //String[] lover={"cruel summer","me"};
        //items.add(new Album("red","12.11.2021",true,red));
        //items.add(new Album("lover","idk",true,lover));


        adapter = new CustomAdapter(this, R.layout.custom_album_row, items);
        lvItems.setAdapter(adapter);
        //User u=new User("name","dfeg","dfgh45");

        //root.push().setValue(new Album("red","12.11.2021",true,red));

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Album album=dataSnapshot.getValue(Album.class);
                    items.add(album);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
            case R.id.itemGames:
                intent=new Intent(this, SpeechActivity.class);
                startActivity(intent);
                break;

            case R.id.itemMusic:
                intent=new Intent(this, MusicActivity.class);
                startActivity(intent);
                break;

            case R.id.itemProfile:
                intent=new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                break;

            case R.id.itemLogout:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("are you sure?");
                builder.setCancelable(false);
                builder.setPositiveButton("yes",this);
                builder.setNegativeButton("No", this);
                AlertDialog dialog=builder.create();
                dialog.show();

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