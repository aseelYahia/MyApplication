package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechActivity2 extends AppCompatActivity implements View.OnClickListener{
    private final int REQ_CODE = 100;
    TextView textView;
    Button speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech2);
        //textView = findViewById(R.id.text);
        speak = findViewById(R.id.bbb);
        speak.setOnClickListener(this);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Speech2", "result");
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.i("Speech2", "if true");
                    textView.setText(result.get(0).toString());
                }
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view==speak)
        {
            Log.i("Speech2", "Speak clicked");
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
            try {
                Log.i("Speech2", "Speak clicked");
                startActivityForResult(intent, REQ_CODE);
            }
            catch (ActivityNotFoundException a) {
                Log.i("Speech2", "caught");
                Toast.makeText(getApplicationContext(),
                        "Sorry your device not supported",
                        Toast.LENGTH_SHORT).show();
            }
        }


    }
}