package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



public class PasswordActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Bundle resultIntent = getIntent().getExtras();

        String genPassword = resultIntent.getString("NewPassword");

        TextView passwordOutput = findViewById(R.id.generated_Password);
        passwordOutput.setText(genPassword);
    }


    }

