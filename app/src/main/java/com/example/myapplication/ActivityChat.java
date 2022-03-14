package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityChat extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnLongClickListener, View.OnClickListener {
    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root=db.getReference("album");
    DatabaseReference myref=db.getReference("Chat");

    CircleImageView prp_view;
    CircleImageView game_1989,game_lover,game_red;

    AlertDialog dialog_prp, dialog_logout;
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatFragment(),"Chats");
        viewPagerAdapter.addFragment(new UserFragment(),"Users");
        viewPager.setAdapter(viewPagerAdapter);



        tabLayout.setupWithViewPager(viewPager);
       // myref.child("writing").setValue("");
        prp_view=findViewById(R.id.prp_view);
        prp_view.setOnLongClickListener(this);

        Intent notifyIntent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 1, pendingIntent);

        image_load(false);

        //go to games
        game_red=findViewById(R.id.game_red);
        game_1989=findViewById(R.id.game_1989);
        game_lover=findViewById(R.id.game_lover);

        game_1989.setOnClickListener(this);
        game_red.setOnClickListener(this);
        game_lover.setOnClickListener(this);


    }
    //change prp
    @Override
    public boolean onLongClick(View view) {
        if(view==prp_view)
        {
            Log.i("Stam","clicked");
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Change your profile picture");
            builder.setCancelable(false);
            builder.setNeutralButton("Cancle",this);
            builder.setNegativeButton("Camera", this);
            builder.setPositiveButton("Gallery",this);
            dialog_prp=builder.create();
            dialog_prp.show();

        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view==game_1989)
        {
            intent=new Intent(this, SpeechActivity.class);
            startActivity(intent);
        }
        if(view==game_red)
        {
            intent=new Intent(this, MadLibsActivity.class);
            startActivity(intent);

        }
        if(view==game_lover)
        {
            intent=new Intent(this, WordleActivity.class);
            startActivity(intent);
        }
    }


    class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }
        @Override
        public Fragment getItem(int position){
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);

        }
        public CharSequence getPageTitle(int postion)
        {
            return titles.get(postion);

        }


    }//class ViewpagerAdapter


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
                dialog_logout=builder.create();
                dialog_logout.show();

                break;


        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionItemSelected

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent;
        if(dialog==dialog_logout)
        {
            if(which==dialog.BUTTON_POSITIVE){
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
            if(which==dialog.BUTTON_NEGATIVE){
                dialog.cancel();
            }
        }
        else if(dialog==dialog_prp)
        {
            if(which==dialog.BUTTON_NEUTRAL) //cancle
            {
                dialog.cancel();
            }
            if(which==dialog.BUTTON_NEGATIVE)//camera
            {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            }
            if(which==dialog.BUTTON_POSITIVE)//gallery
            {
                intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_IMAGE);
            }


        }


    }//clicked dialog button
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                //the image captured is saved in the data object
                bitmap = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                //prp_view.setImageBitmap(bitmap);
                image_load(true);


            }
        }//if
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            //URI - unified resource locator is something like URL but can hold different type of paths
            // examples: file:///something.txt, http://www.example.com/, ftp://example.com
            // A Uri object is usually used to tell a ContentProvider what we want to access by reference
            Uri targetUri = data.getData();
            try {
                //Decode an input stream into a bitmap.
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                prp_view.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }//onactivityresult

    //database string an bitmap stuff
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        Log.i("Stam","string bitmap"+temp);

        return temp;
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    public void image_load(boolean isNew)
    {

        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        String uId=mAuth.getCurrentUser().getUid();
        DatabaseReference ref=db.getReference("Users/"+uId+"/UserObject/"+uId+"/img");
        if(isNew){
            String new_pic=BitMapToString(bitmap);
            ref.setValue(new_pic);
        }
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String k="img";
                String img = snapshot.getValue(String.class);
                if(!(img.equals("no pic yet")))
                    prp_view.setImageBitmap(StringToBitMap(img));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}