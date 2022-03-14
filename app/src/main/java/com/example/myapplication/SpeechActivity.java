package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    private TextView textviewspoken,textViewlyrics;
    private Button buttonmic;
    private int count;
    private Intent recognizerIntent;
    static String LOG_TAG = "VoiceRecognitionActivity";
    private final int REQ_CODE = 100;
    String guessed_name;
    int count_times, number_song;
    boolean isNext=true;
    int score=0;
    String[][] songs= {
            {"lover", "my my my"},
            {"wildest dreams", "staring at the sunset"},
            {"closure", "Im fine with my spites and my tears and my candles"},
            {"you all over me", "no amount of freedom gets you clean"},
            {"cowboy like me", "the skeleton in  both our closets"},
            {"Miss Americana and the Heartbreak prince", "you play stupid games you win stupid prizes"},
            {"happiness", "sorry i cant see the facts through all of my fury"},
            {"The last great American dynasty", "I had a marvelour time ruining everything"},
            {"new year's day", "please dont ever become a stranger whos laugh i could recignize anywhere"},
            {"cardigan", "you drew start around my scars but now im bleeding"},
            {"new romantics", "cause everyday is like a battle but every night with us is like a dream"},
            {"call it what you want", "i brought a knife to a gun fight"},
            {"don't blame me ", "your love made me crazy if it doesnt you aint doing it right"}
    };;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        stopService(MainActivity.musicIntent);
        count_times=0;

        buttonmic = findViewById(R.id.buttonmic);
        textViewlyrics = findViewById(R.id.textViewlyrics);
        textviewspoken = findViewById(R.id.textViewspoken);

        buttonmic.setOnClickListener(this);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Speech", "result");
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.i("Speech2", "if true");
                    //textViewlyrics.setText(result.get(0).toString());
                    guessed_name=result.get(0).toString();
                    if(guessed_name.toLowerCase().equals(songs[number_song][0].toLowerCase())){
                        textviewspoken.setText("correct! " +guessed_name+" is the answer ;)");
                        score++;
                    }
                    else{
                        textviewspoken.setText("incorrect :( "+ guessed_name+" is not the answer, the song is "+songs[number_song][0]);
                    }
                    buttonmic.setText("Next");
                    isNext=true;
                }
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        //show question
        if(view == buttonmic && isNext)
        {
            if(count_times>=6)
            {
                buttonmic.setEnabled(false);
                textviewspoken.setText("your score is"+score+"<3");
                textViewlyrics.setText("The end");
            }
            else{
            number_song=(int)(Math.random()*songs.length);
            textViewlyrics.setText(songs[number_song][1]);
            textviewspoken.setText("");
            buttonmic.setText("answer");
            isNext=false;
            count_times++;}


        }
        else if(view == buttonmic)
        {
            Log.i("Speech", "Speak clicked");
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
            try {
                Log.i("Speech", "Speak clicked");
                startActivityForResult(intent, REQ_CODE);
            }
            catch (ActivityNotFoundException a) {
                Log.i("Speech", "caught");
                Toast.makeText(getApplicationContext(),
                        "Sorry your device not supported",
                        Toast.LENGTH_SHORT).show();
            }
        }
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