package com.example.realtimesignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText loginname,loginpass;
    FirebaseDatabase database;
    DatabaseReference reference;

    Button login;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginname=findViewById(R.id.login_name);
        loginpass=findViewById(R.id.login_password);
        login=findViewById(R.id.Login);
        signup=findViewById(R.id.SignupRedirectText);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateemail() | !validatepass()){


                }
                else {
                    checkUser();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateemail(){
        String val=loginname.getText().toString();
        if(val.isEmpty())
        {
            loginname.setError("Email cannot be void");
            return false;
        }
        else
        {
            loginname.setError(null);
            return true;
        }
    }
    public Boolean validatepass(){
        String val=loginpass.getText().toString();
        if(val.isEmpty())
        {
            loginpass.setError("Pass cannot be void");
            return false;
        }
        else
        {
            loginpass.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String user=loginname.getText().toString();
        String pass=loginpass.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdb= reference.orderByChild("name").equalTo(user);
        Log.d("k",user);

        checkuserdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    loginname.setError(null);
                    String passfromDb = snapshot.child(user).child("pass").getValue(String.class);
                    Log.d("o",passfromDb);
                    Log.d("p",pass);

                    if (passfromDb.equals(pass)) {
                        loginname.setError(null);
                        String email=snapshot.child(user).child("email").getValue(String.class);
                        String pass=snapshot.child(user).child("pass").getValue(String.class);
                        Intent intent= new Intent(Login.this,MainActivity.class);

                        intent.putExtra("name",user);
                        intent.putExtra("email",email);
                        intent.putExtra("pass",pass);

                        startActivity(intent);
                    }
                    else
                    {
                        loginname.setError("Invalid Credentials");
                        loginpass.requestFocus();
                    }
                }
                else
                {
                    loginname.setError("User doesn't exists");
                    loginname.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}