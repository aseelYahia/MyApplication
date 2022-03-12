package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MadLibsActivity extends AppCompatActivity implements View.OnClickListener{
    EditText[] edits=new  EditText[15];
    Button submit;
    String final_piece;
    int bonus=0;
    TextView final_piece_tv,bonus_tv;
    String[] iconic_ts={
       "Coffee",
       "CheeseBurger",
       "Cats",
       "Snake",
       "Door",
       "Window",
       "Chearleader",
       "singing",
       "playing guitar",
       "car",
       "unicorn",
       "dress",
       "butterfly",
       "romeo",
       "candle",
       "phone",
       "sparks",
       "rain"
    } ;
    String[] sentences= {
            "And you were tossing me the car ",
            " oops the ",
            ",",
            " on the ground, we were always ",
            " town\n"+"And I was ",
            " on the drive down, any time now\n" + "He's gonna say it's ",
            " , you never ",
            " it what it was\n" + "'Til we were ",
            " and ",
            " and buried\n" + "Check the ",
            " and come back swearing it's the same\n" + "After three months in the ",
            "\n" + "And then you wondered where it",
            " to as I reached for ",
            "\n" + "But all I felt was ",
            " and you held my lifeless "
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_libs);
        edits[0]= findViewById(R.id.edit1);
        edits[1]= findViewById(R.id.edit2);
        edits[2]= findViewById(R.id.edit3);
        edits[3]= findViewById(R.id.edit4);
        edits[4]= findViewById(R.id.edit5);
        edits[5]= findViewById(R.id.edit6);
        edits[6]= findViewById(R.id.edit7);
        edits[7]= findViewById(R.id.edit8);
        edits[8]= findViewById(R.id.edit9);
        edits[9]= findViewById(R.id.edit10);
        edits[10]= findViewById(R.id.edit11);
        edits[11]= findViewById(R.id.edit12);
        edits[12]= findViewById(R.id.edit13);
        edits[13]= findViewById(R.id.edit14);
        edits[14]= findViewById(R.id.edit15);

        submit=findViewById(R.id.submit_words_btn);
        submit.setOnClickListener(this);

        final_piece_tv=findViewById(R.id.final_piece_tv);
        bonus_tv=findViewById(R.id.bonus_tv);
    }

    @Override
    public void onClick(View view) {
        if(view==submit)
        {
            final_piece="";
            String temp;
            for(int i=0;i<edits.length;i++)
            {
                //get the answers and add togerther to one piece
                temp=edits[i].getText().toString().toLowerCase();
                final_piece+=sentences[i]+(temp.toUpperCase());
                edits[i].setVisibility(view.INVISIBLE);

                //percentage of taylor words- iconic taylor swift items
                boolean found=false;
                for(int j=0;j<iconic_ts.length;j++)
                {
                    if(temp.equals(iconic_ts[j].toLowerCase()))
                    {
                        found=true;
                    }
                }
                if (found)
                {
                    bonus+=10;
                }
            }
            bonus_tv.setText("YOUR BONUS FOR HAVING ICONIC TS ITEMS IS\n "+bonus);
            submit.setVisibility(view.INVISIBLE);
            final_piece_tv.setText(final_piece);
        }

    }
}

/*
I walked through the DOOR=MAGIC REALM with you
the air was COLD= ADJECTIVE, but something about it felt like HOME=LIQUID
I left my SCARF= there are your SISTER'S = PROFFESIONAL house
and you still got it, In your DRAWERE=TRANSPORTATION MEAN even now
Oh, your sweet DESPOSITION= furniture and my wide-eyed GAZE=SEA ANIMAL
We're SINING= VERB+ING in the car, getting LOST=VERB+ING upstate
Autumn LEAVES= CLOTHING ITEM falling down like pieces into place
And I can PICTURE= VERB it after all these days


And I know it's long gone and
That MAGIC'S=COFFEE ORDER  not here no more
And I might be OKAY== ADJECTIVE, but I'm not fine at all
Oh, oh, oh

'Cause there we are again on that LITTLE= ADJECTIVE town STREET= COFFEE SHOW
You almost ran the RED= FRUIT 'cause you were lookin' over at ME= MOVIE NAME
WIND=ANIMAL in my hair, I was there
I remember it all too well

PHOTO= CAR BRAND album on the counter, your cheeks were turning RED=COLOR
You used to be a little KID=BIRD TYPE with glasses in a twin-sized BED= FURNITURE
And your mother's SELLING= VERB+ING stories 'bout you on the TEE-BALL=REALITY TV SHOW team
You TAUGHT=VERB me 'bout your PAST=TYPE OF FEAR, thinking your future was ME=ADJECTIVE

And you were tossing me the car KEY= CUTLURY, "fuck the PATRIARCY=DESERT"
KEY CHAIN= BODY PART on the ground, we were always SKIPPING=VERB+ING town
And I was THINKING= VERB=ING on the drive down, any time now
He's gonna say it's LOVE=STH GROSS , you never CALLED= VERB+ED it what it was
'Til we were DEAD=ADJ and GONE= ADJ and buried
Check the PULSE=TEST TYPE and come back swearing it's the same
After THREE MONTHS= PERIOD OF TIME  in the grave
And then you wondered where it WENT=PAST VERB to as I reached for YOU= WILD ANIMAL
But all I felt was SHAME= EMOTION and you held my lifeless FRAME= MITHICAL CREATURE

And I know it's long gone and
That MAGIC'S=COFFEE ORDER  not here no more
And I might be OKAY== ADJECTIVE, but I'm not fine at all
Oh, oh, oh

* Dancing around the KITCHEN=TYPE OF DRINK in the refrigderator LIGHT=SMELL
* down the STAIRS I was there I REMEBER=VERB PRESENT SIMPLE it all too well
*

And there we are again when nobody had to know
You kept me like a secret, but I kept you like an oath
Sacred prayer and we'd swear
To remember it all too well, yeah

Well, maybe we got lost in translation
Maybe I asked for too much
But maybe this thing was a masterpiece
'Til you tore it all up
Running scared, I was there
I remember it all too well
And you call me up again just to break me like a promise
So casually cruel in the name of being honest
I'm a crumpled up piece of paper lying here
'Cause I remember it all, all, all

 */

/*      TextView hhh;
        hhh=findViewById(R.id.hhh);
        float radius = hhh.getTextSize() / 3;
        BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
        hhh.getPaint().setMaskFilter(filter);
    */