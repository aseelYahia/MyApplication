package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
    private static final String TAG = "FIREBAE";
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    private FirebaseAuth mAuth;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        //a refrence to the existing database-the instance of the project firebase
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
        String em=pref.getString("email",null);
        String pwd=pref.getString("password",null);
        if (em!=null && pwd!=null){
            editTextEmail.setText(em);
            editTextPassword.setText(pwd);
        }



    }
    @Override
    public void onClick(View view) {
        if(view==buttonLogin)
        {
            String name=editTextEmail.getText()+"";
            login(editTextEmail.getText().toString(),editTextPassword.getText().toString());
            SharedPreferences pref = getSharedPreferences("mypref",MODE_PRIVATE);
            editor= pref.edit();
            editor.putString("email",editTextEmail.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());
            editor.commit();
            //Intent intent=new Intent(this, WelcomeActivity.class);
            //intent.putExtra("name",name);
            //startActivity(intent);


        }

    }//onclick
    public void login(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //User u=new User("name",email,user.getUid());
                            //FirebaseDatabase db=FirebaseDatabase.getInstance();
                            //DatabaseReference root=db.getReference().child("User");
                            //root.push().setValue(u);

                            Intent intent=new Intent(LoginActivity.this, ActivityChat.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }//login

}