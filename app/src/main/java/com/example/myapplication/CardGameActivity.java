package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CardGameActivity extends AppCompatActivity {
    Song[][] songs ={{
            new Song("lover", "Cornilia street"),
            new Song("lover", "The archer"),
            new Song("lover", "Cruel summer"),
            new Song("lover", "London boy"),
            new Song("lover", "Paper rings"),
            new Song("fearless", "Love Story"),
            new Song("fearless", "the best day"),
            new Song("fearless", "Forever & always"),
            new Song("fearless", "Hey Stephen"),
            new Song("fearless", "White horse"),
            new Song("red", "Holy Ground"),
            new Song("red", "All too well"),
            new Song("red", "The very first night"),
            new Song("red", "The last time"),
            new Song("red", "The moment I knew"),
            new Song("evermore", "Champagne problems"),
            new Song("evermore", "Ivy"),
            new Song("evermore", "cowboy like me"),
            new Song("evermore", "willow"),
            new Song("evermore", "gold rush"),
            new Song("folklore", "exile"),
            new Song("folklore", "cardigan"),
            new Song("folklore", "betty"),
            new Song("folklore", "tears ricochet"),
            new Song("folklore", "hoax")},
    {
            new Song("Debut", "our song"),
            new Song("Debut", "pictures to burn"),
            new Song("Debut", "Teardrops on my guitar"),
            new Song("Debut", "Tim McGraw"),
            new Song("Debut", "Should've said no "),
            new Song("speak now", "Better than revenge"),
            new Song("speak now", "The story of us"),
            new Song("speak now", "Spark fly"),
            new Song("speak now", "Enchanted"),
            new Song("speak now", "Dear John"),
            new Song("reputation", "Getaway car"),
            new Song("reputation", "New year's day"),
            new Song("reputation", "I did something bad"),
            new Song("reputation", "Dress"),
            new Song("reputation", "Dancing with our hands tied"),
            new Song("1989", "Wonderland"),
            new Song("1989", "new romantics"),
            new Song("1989", "blank space"),
            new Song("1989", "I know places"),
            new Song("1989", "Wildest dreams")
    }};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);
    }
}