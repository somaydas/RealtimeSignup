package com.example.realtimesignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
        EditText name,email,pass;
        name=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_email);
        pass=findViewById(R.id.signup_password);

        name.setText(intent.getStringExtra("name"));
        email.setText(intent.getStringExtra("email"));
        pass.setText(intent.getStringExtra("pass"));






    }
}