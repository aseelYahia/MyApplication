package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class BirthdayActivity extends AppCompatActivity {
    KonfettiView celebration;
    boolean hasReason=false;

    TextView date_box, event_announcement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        //views
        date_box=findViewById(R.id.date_box);
        event_announcement=findViewById(R.id.event_announcement);


        //date hot stuff
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        Log.i("Birthday",df+" hell "+df.format(c));
        String current=df.format(c).toString();
        date_box.setText(current);
        String taybday= "13-Dec-1989";
        ///SimpleDateFormat ddd=new SimpleDateFormat("dd-MMM-yyy",new Date(2000,4,9));
        Log.i("Birthday", "  ddddd "+current+" tttt "+taybday );

        if (current.equals(getString(R.string.today_date))){
          Log.i("Birthday", "omg this thing might sctually work ");
          event_announcement.setText("No reason ");
        }
        else if (current.equals(getString(R.string.debut_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Debut");

        }
        else if (current.equals(getString(R.string.fearless_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Fearless");

        }
        else if (current.equals(getString(R.string.speak_now_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Speak now");

        }
        else if (current.equals(getString(R.string.red_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Red");

        }
        else if (current.equals(getString(R.string.n1989_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("1989");

        }
        else if (current.equals(getString(R.string.reputation_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Reputation");

        }
        else if (current.equals(getString(R.string.lover_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Lover");

        }
        else if (current.equals(getString(R.string.folklore_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Folklore");

        }
        else if (current.equals(getString(R.string.evermore_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Evermore");

        }
        else if (current.equals(getString(R.string.fearless_tv_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Fearless TV");

        }
        else if (current.equals(getString(R.string.red_tv_date))){
            Log.i("Birthday", "omg this thing might sctually work ");
            event_announcement.setText("Red TV");

        }
        else if (current.equals("28-Feb-2022"))
        {
            Log.i("Birthday", "same date yey:) ");

        }

        //frigin celebration we love konfetti
        celebration= findViewById(R.id.konfettiView);
        crazyCelebration();


    }
    public void crazyCelebration()
    {
        KonfettiView konfettiView = findViewById(R.id.konfettiView);
        EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(emitterConfig)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(5000L)
                .position(0.0, 0.0, 1.0, 0.0)
                .colors(Arrays.asList(0x34f3ff,0x00a3ad,0x6aebb4,0x97e9c1))
                .build();
                konfettiView.start(party);


    }
}