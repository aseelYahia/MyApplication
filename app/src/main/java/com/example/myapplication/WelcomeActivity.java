package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener  {
    TextView textViewIntro;
    Button buttonpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        textViewIntro=findViewById(R.id.textViewIntro);
        buttonpic=findViewById(R.id.buttonPic);
        buttonpic.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            textViewIntro.setText("Welcome "+extras.getString("n"));
    }

    @Override
    public void onClick(View view) {
        if(view== buttonpic)
        {
            Intent intent=new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
    }//onClick

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