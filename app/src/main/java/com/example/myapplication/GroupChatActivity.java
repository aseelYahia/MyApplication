package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.MessagesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupChatActivity extends AppCompatActivity implements  View.OnClickListener{
    //msgs adapter
    ListView message_listview;
    ArrayList<Message> m;
    MessagesAdapter messagesAdapter;
    //vars
    String chatName,thetext;
    User u;
    //views
    TextView title_groupChat;
    ImageButton sendbtn;
    EditText sendText;

    //firebase
    FirebaseAuth mAuth;
    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        title_groupChat=findViewById(R.id.title_groupChat);
        message_listview=findViewById(R.id.message_listView);
        sendbtn=findViewById(R.id.send_image_button);
        sendbtn.setOnClickListener(this);
        sendText=findViewById(R.id.send_text);
        //get chat name
        Bundle bundle = getIntent().getExtras();
        chatName="Error-doesn't exist ";
        if(bundle.getString("n")!= null)
        {
            chatName=bundle.getString("n");
            title_groupChat.setText(chatName);
        }
        root=db.getReference("Message/"+chatName);
        //current user
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        u=new User("name",user.getEmail(),"jjj");

        //display chat msgs
        m= new ArrayList<>();
        //Message msg=new Message(chatName,u,"heheh");
        //m.add(msg);
        messagesAdapter =new MessagesAdapter(this,R.layout.message_row,m);
        message_listview.setAdapter(messagesAdapter);

        //get all msgs from db
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Message item=dataSnapshot.getValue(Message.class);
                    Log.i("Stam",item.getChat_name());
                    m.add(item);
                    messagesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //image




    }

    @Override
    public void onClick(View view) {
        if (view==sendbtn)
        {
            thetext=sendText.getText().toString();
            Message msg=new Message(chatName,u,thetext);
            db.getReference("Message/"+chatName).push().setValue(msg);
            sendText.setText("");
        }

    }
}