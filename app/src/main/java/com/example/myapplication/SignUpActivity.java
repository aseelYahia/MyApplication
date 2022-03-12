package com.example.myapplication;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPersonName,editTextBday , editTextPassword;
    private Button buttonSubmit;
    private FirebaseAuth mAuth;
    String name;
    FirebaseDatabase db=FirebaseDatabase.getInstance("https://taylor-ff1e9-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference root;
    private static final String TAG = "FIREBAE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextPassword=findViewById(R.id.editTextPassword);


        //db stuff
        mAuth = FirebaseAuth.getInstance();

    }



    @Override
    public void onClick(View view) {
        if(view==buttonSubmit)
        {
            String email=editTextEmail.getText().toString().trim();
            String password=editTextPassword.getText().toString().trim();
            name=editTextPersonName.getText()+" ";
            signUp(email,password);

        }

    }//onClick

    public boolean checkPassword(String str)
    {
        Toast.makeText(getApplicationContext(),"in check password", Toast.LENGTH_SHORT).show();
        char ch;
        boolean capitalFlag = false;
        boolean specialCharFlag= false;
        boolean numberFlag = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
            else if (ch>=33 && ch<=47)
            {
                specialCharFlag = true;
            }
            if(numberFlag && capitalFlag && specialCharFlag)
                return true;
        }
        return false;
    }//check password
    public void signUp(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password) //activates a firebase function
                //stays listening till an answer comes back from the db
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //auth was succesful
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser(); //the user that is connected-logged in
                            String uId= user.getUid();
                            root=db.getReference("Users/"+uId+"/UserObject/"+uId);
                            User u=new User("ss",email,"rep");
                            root.setValue(u);
                            Intent intent=new Intent(SignUpActivity.this, WelcomeActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }




}