package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityChat extends AppCompatActivity implements  DialogInterface.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root=db.getReference("album");
    DatabaseReference myref=db.getReference("Chat");

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
            case R.id.itemGames:
                intent=new Intent(this, SpeechActivity.class);
                startActivity(intent);
                break;

            case R.id.itemMusic:
                intent=new Intent(this, BirthdayActivity.class);
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