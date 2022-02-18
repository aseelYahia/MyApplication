package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.ChatListAdapter;
import com.example.myapplication.ui.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class ChatFragment extends Fragment {
    ArrayList<Chat> items;
    ListView chatListView;
    ChatListAdapter chatListAdapter;
    ListView chats_rows;
    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_chat,container,false);
        chatListView=view.findViewById(R.id.chatListView);
        items= new ArrayList<>();
        items.add(new Chat("lover", "1111","fghj"));
        chatListAdapter =new ChatListAdapter(container.getContext(),R.layout.chat_item,items);
        chatListView.setAdapter(chatListAdapter);
        //data from firebase displayed in listview

        root=db.getReference("Chat");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Chat chat=dataSnapshot.getValue(Chat.class);
                    items.add(chat);
                    chatListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //allow pressing on item
     chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent intent = new Intent(container.getContext(),GroupChatActivity.class);
             Chat c=items.get(i);
             intent.putExtra("n",c.getName());
             startActivity(intent);
         }
     });

        return view;
    }


}