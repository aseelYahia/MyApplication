package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WriteSongActivity extends AppCompatActivity implements View.OnClickListener {
    //views
    Button savebtn;
    EditText edit_title, write_lyrics;
    RecyclerView recycler_cardlyric;

    //variables
    String title,lyrics;
    User u;
    ArrayList<Lyric> saved_lyric;

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
        //get current user
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        u=new User(user.getUid().toString(), user.getEmail());

        //display lyrics
        root=db.getReference("Users/"+user.getUid()+"/Lyrics");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Lyric l= dataSnapshot.getValue(Lyric.class);
                    saved_lyric.add(l);

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
            Lyric lyric_item= new Lyric(title,lyrics,u);
            db.getReference("Users/"+user.getUid()+"/Lyrics").push().setValue(lyric_item);

        }

    }
}