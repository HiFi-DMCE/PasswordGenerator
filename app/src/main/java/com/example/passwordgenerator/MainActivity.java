package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    int pos =0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        SeekBar sk;
        TextView textView;
        Switch sw1,sw2,sw3,sw4;

        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.btn);
        sk = findViewById(R.id.sk);
        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        sw3 = findViewById(R.id.sw3);
        sw4 = findViewById(R.id.sw4);



        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pos=i;
                textView.setText("Length:"+pos);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick (View v) {
                if(pos>0){


                    Boolean c1 = sw1.isChecked();
                    Boolean c2 = sw2.isChecked();
                    Boolean c3 = sw3.isChecked();
                    Boolean c4 = sw4.isChecked();

                    Toast.makeText(MainActivity.this, "Password Generated Successfully", Toast.LENGTH_SHORT).show();

                    Intent iBtn = new Intent(MainActivity.this, PasswordActivity.class);
                    Bundle extra = new Bundle();
                    extra.putInt("val",pos);
                    extra.putBoolean("1",c1);
                    extra.putBoolean("2",c2);
                    extra.putBoolean("3",c3);
                    extra.putBoolean("4",c4);
                    iBtn.putExtra("b",extra);
                    startActivity(iBtn);}
                else{
                    Toast.makeText(MainActivity.this, "Invalid Length", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

}




