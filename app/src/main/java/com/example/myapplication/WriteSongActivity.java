package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.LyricRecycleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WriteSongActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    //views
    Button savebtn;
    EditText edit_title, write_lyrics;
    RecyclerView recycler_cardlyric;
    LyricRecycleAdapter adapter;
    //variables
    String title,lyrics;
    User u;
    ArrayList<Lyric> saved_lyric;
    String uId;
    //firebase
    FirebaseAuth mAuth;
    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_song);
        edit_title=findViewById(R.id.editview_lyricstitle);
        write_lyrics=findViewById(R.id.editview_lyricstitle);
        savebtn=findViewById(R.id.button_save_lyrics);
        savebtn.setOnClickListener(this);

        recycler_cardlyric=findViewById(R.id.recycler_cardlyric);
        //get current user
        mAuth = FirebaseAuth.getInstance();
        uId = mAuth.getCurrentUser().getUid();

        saved_lyric=new ArrayList<>();
        adapter =new LyricRecycleAdapter(this,saved_lyric);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recycler_cardlyric.setLayoutManager(gridLayoutManager);
        recycler_cardlyric.setHasFixedSize(true);

        recycler_cardlyric.setAdapter(adapter);

        //display prev stuff
        root=db.getReference("Users/"+uId+"/Lyrics");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Write","in add value");
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Lyric lyric=dataSnapshot.getValue(Lyric.class);
                    saved_lyric.add(lyric);
                    Log.i("Write","datasnapshot"+lyric.toString());
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    @Override
    public void onClick(View view) {
        if (view==savebtn)
        {
            title=edit_title.getText().toString();
            lyrics=write_lyrics.getText().toString();
            Lyric lyric_item= new Lyric(title,lyrics);
            db.getReference("Users/"+uId+"/Lyrics").push().setValue(lyric_item);

        }

    }


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