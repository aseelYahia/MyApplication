package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    EditText editTextEmail, editTextPassword;
    Button buttonLogin, buttonSignUp;
    private Intent musicIntent;


    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);
        root=db.getReference("Chat");
        //root.push().setValue(new Chat("red","12.11.2021","img"));



        //musicIntent=new Intent(this,MusicService.class);
        //startService(musicIntent);



    }


    @Override
    public void onClick(View view) {
        if(view==buttonLogin)
        {
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
        if(view==buttonSignUp)
        {
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivity(intent);
        }





    }//onclicl

}