package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class WordleActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    //row 1 edit texts
    EditText l1r1, l2r1,l3r1,l4r1,l5r1;
    EditText l1r2,l2r2,l3r2,l4r2,l5r2;
    EditText l1r3,l2r3,l3r3,l4r3,l5r3;
    EditText l1r4,l2r4,l3r4,l4r4,l5r4;
    EditText l1r5,l2r5,l3r5,l4r5,l5r5;
    Button check1,check2,check3,check4,check5;
    int index,count=-1;
    String[] words={"lover","prize","heart","break"};
    EditText[][] edits= new EditText[5][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle);
       //row 1
        edits[0][0]=findViewById(R.id.letter1row1);
        edits[0][1]=findViewById(R.id.letter2row1);
        edits[0][2]=findViewById(R.id.letter3row1);
        edits[0][3]=findViewById(R.id.letter4row1);
        edits[0][4]=findViewById(R.id.letter5row1);
        check1=findViewById(R.id.check_row1);
        check1.setOnClickListener(this);
        //row2
        edits[1][0]=findViewById(R.id.letter1row2);
        edits[1][1]=findViewById(R.id.letter2row2);
        edits[1][2]=findViewById(R.id.letter3row2);
        edits[1][3]=findViewById(R.id.letter4row2);
        edits[1][4]=findViewById(R.id.letter5row2);
        check2=findViewById(R.id.check_row2);
        check2.setOnClickListener(this);
        //row3
        edits[2][0]=findViewById(R.id.letter1row3);
        edits[2][1]=findViewById(R.id.letter2row3);
        edits[2][2]=findViewById(R.id.letter3row3);
        edits[2][3]=findViewById(R.id.letter4row3);
        edits[2][4]=findViewById(R.id.letter5row3);
        check3=findViewById(R.id.check_row3);
        check3.setOnClickListener(this);
        //row4
        edits[3][0]=findViewById(R.id.letter1row4);
        edits[3][1]=findViewById(R.id.letter2row4);
        edits[3][2]=findViewById(R.id.letter3row4);
        edits[3][3]=findViewById(R.id.letter4row4);
        edits[3][4]=findViewById(R.id.letter5row4);
        check4=findViewById(R.id.check_row4);
        check4.setOnClickListener(this);
        //row 5
        edits[4][0]=findViewById(R.id.letter1row5);
        edits[4][1]=findViewById(R.id.letter2row5);
        edits[4][2]=findViewById(R.id.letter3row5);
        edits[4][3]=findViewById(R.id.letter4row5);
        edits[4][4]=findViewById(R.id.letter5row5);
        check5=findViewById(R.id.check_row5);
        check5.setOnClickListener(this);
        //geenrate word :)
        index= (int) (Math.random()*words.length);

        //disables checks
        check2.setEnabled(false);
        check3.setEnabled(false);
        check4.setEnabled(false);
        check5.setEnabled(false);

        Log.i("Wordle", "generated word: "+words[index]);

//start over button
        //add more words to array pls
        //prettier colors search their rgba
        //end with dialog announcing win
        //taylor game general score and ratings in birthday page
        //7abibi add instructions pls



    }

    public int isInWord(String word, char let)
    {
        Log.i("Wordle", "isInWord");

        int foundIdx=-1;
        for (int i=0; i<word.length();i++)
        {
            if (word.charAt(i)==let)
            {
                foundIdx=i;
            }
        }
        Log.i("Wordle", "index found letter in "+foundIdx);

        return foundIdx;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent;
        if(which==dialog.BUTTON_POSITIVE){
            intent=new Intent(this,WordleActivity.class);
            startActivity(intent);
            dialog.cancel();
        }
        if(which==dialog.BUTTON_NEGATIVE){
            dialog.cancel();
        }

    }

    public void showEndDialoge(boolean isWin)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if(isWin) {
            builder.setMessage("guess what? you play stupid games you win stupid prizes so here you go congrats luv! you get 5 cat points ^^ ");
        }
        else{
            builder.setMessage("you lost this time but all you have to do it try try try and make Tay Tay proud proud proud luv!");
        }
        builder.setCancelable(false);
        builder.setPositiveButton("Play again", this);
        builder.setNegativeButton("Done",  this);
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    @Override
    public void onClick(View view) {
        String guess="";
        int correct=0;
        if(view==check1){
            count=0;
            check1.setEnabled(false);
            check2.setEnabled(true);
        }
        else if(view ==check2){
            count=1;
            check2.setEnabled(false);
            check3.setEnabled(true);
        }
        else if(view ==check3){
            count=2;
            check3.setEnabled(false);
            check4.setEnabled(true);

        }
        else if(view ==check4){
            count=3;
            check4.setEnabled(false);
            check5.setEnabled(true);

        }
        else if(view ==check5){
            count=4;
            check5.setEnabled(false);
        }
        if (count !=-1) {
            Log.i("Wordle", "clicked"+count);
            for (int i=0;i<5;i++)
            {
                guess+=edits[count][i].getText().toString();
            }

            for (int i=0;i<5; i++)
            {
                int foundIdx=isInWord(words[index],guess.charAt(i));
                //not found at all
                if(foundIdx ==-1)
                {
                    edits[count][i].setBackgroundColor(Color.GRAY);
                }
                //found in the same idx
                else if (foundIdx==i)
                {
                    edits[count][i].setBackgroundColor(Color.GREEN);
                    correct++;
                }
                //found in a diff idx
                else {
                    edits[count][i].setBackgroundColor(Color.YELLOW);

                }

            }//end of for loop
            if(correct==5){
                Log.i("Wordle", "WON GAME HONEY");
                check1.setEnabled(false);
                check2.setEnabled(false);
                check3.setEnabled(false);
                check4.setEnabled(false);
                check5.setEnabled(false);
                showEndDialoge(true);
            }
            if( count==4 && correct<5)
            {
                Log.i("Wordle", "didn't win");
                check1.setEnabled(false);
                check2.setEnabled(false);
                check3.setEnabled(false);
                check4.setEnabled(false);
                check5.setEnabled(false);
                showEndDialoge(false);
            }
        }//-1

    }
}