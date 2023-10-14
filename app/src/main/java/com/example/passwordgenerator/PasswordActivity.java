package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.security.SecureRandom;

public class PasswordActivity extends AppCompatActivity {
    int len ;
    Boolean c1,c2,c3,c4;
    TextView tV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        Bundle extras = getIntent().getExtras();
        len = extras.getInt("val");
         /*c1 = extras.getBoolean("1");
         c2 = extras.getBoolean("2");
         c3 = extras.getBoolean("3");
         c4 = extras.getBoolean("4");*/
        Boolean c1 =true,c2=true,c3=true,c4=true;
        String pass = Generate(len,c1,c2,c3,c4);
        tV=findViewById(R.id.tv);

        tV.setText(pass);

        Log.d("PasswordActivity", "len: " + len);
        Log.d("PasswordActivity", "c1: " + c1);
        Log.d("PasswordActivity", "c2: " + c2);
        Log.d("PasswordActivity", "c3: " + c3);
        Log.d("PasswordActivity", "c4: " + c4);


    }

    private String Generate(int len,Boolean c1,Boolean c2,Boolean c3,Boolean c4)
    {

        String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUMBERS = "012345679";
        String SPECIAL_SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>/?";
        String VALID_CHARACTERS="";
        if(c1==true) {
            VALID_CHARACTERS += LOWERCASE_CHARACTERS;
        }
        if(c2==true){
            VALID_CHARACTERS += UPPERCASE_CHARACTERS;
        }
        if(c3==true){
            VALID_CHARACTERS += NUMBERS;
        }
        if(c4==true){
            VALID_CHARACTERS += SPECIAL_SYMBOLS;
        }


        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for(int i=0;i<len;i++){
            int index=random.nextInt(VALID_CHARACTERS.length());
            sb.append(VALID_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
