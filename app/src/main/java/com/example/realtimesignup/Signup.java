package com.example.realtimesignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {

    EditText signemail,signname,signpass;
    TextView loginRedirect;
    Button Signup;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signname=findViewById(R.id.signup_name);
        signemail=findViewById(R.id.signup_email);
        signpass=findViewById(R.id.signup_password);
        Signup=findViewById(R.id.Signup);
        loginRedirect=findViewById(R.id.loginRedirectText);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name=signname.getText().toString();
                String email=signemail.getText().toString();
                String pass=signpass.getText().toString();

                Helper helper= new Helper(name,email,pass);
                reference.child(name).setValue(helper);

                Toast.makeText(Signup.this,"Signed Up successfully",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Signup.this,Login.class);
                startActivity(intent);

            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

    }
}