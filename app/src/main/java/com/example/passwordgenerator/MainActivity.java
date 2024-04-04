package com.example.passwordgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;


public class MainActivity extends AppCompatActivity {
    int pos = 0;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        SeekBar sk;
        TextView textView;
        Switch sw1, sw2, sw3, sw4;

        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.btn);
        sk = findViewById(R.id.sk);
        sw1 = findViewById(R.id.sw1);
        sw2 = findViewById(R.id.sw2);
        sw3 = findViewById(R.id.sw3);
        sw4 = findViewById(R.id.sw4);

        sharedPreferences = getSharedPreferences("tutorial", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("isFirstTime", true);
        isFirstTime=true;
        if (isFirstTime) {
            showTutorialDialog();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();
        }

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pos = i;
                textView.setText("Length:" + pos);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Show tap target when the user stops dragging the seek bar
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            Boolean c1, c2, c3, c4;

            @Override
            public void onClick(View v) {
                c1 = sw1.isChecked();
                c2 = sw2.isChecked();
                c3 = sw3.isChecked();
                c4 = sw4.isChecked();
                if ((pos > 0) && ((c1 != false) || (c2 != false) || (c3 != false) || (c4 != false))) {
                    Intent iBtn = new Intent(MainActivity.this, PasswordActivity.class);
                    iBtn.putExtra("val", pos);
                    SharedPreferences pref = getSharedPreferences("main", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("c1", c1);
                    editor.putBoolean("c2", c2);
                    editor.putBoolean("c3", c3);
                    editor.putBoolean("c4", c4);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Password Generated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(iBtn);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Entry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showTutorialDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Welcome to Password Generator");
            builder.setMessage("Would you like a quick guide on how to use this app?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show tutorial
                    showTutorialSequence();
                }
            });
            builder.setNegativeButton("No", null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show a Toast indicating the error)
        }
    }


    private void showTutorialSequence() {
        TapTargetSequence sequence = new TapTargetSequence(this)
                .targets(
                        TapTarget.forView(findViewById(R.id.sk), "Adjust Length", "Drag the seek bar to adjust the length of your password")
                                .outerCircleColor(R.color.ocean)
                                .targetCircleColor(android.R.color.white)
                                .textColor(android.R.color.white)
                                .cancelable(true),
                        TapTarget.forView(findViewById(R.id.sw1), "Toggle Switches", "Toggle the switches to include/exclude special characters, numbers, etc.")
                                .outerCircleColor(R.color.ocean)
                                .targetCircleColor(android.R.color.white)
                                .textColor(android.R.color.white)
                                .cancelable(true),
                        TapTarget.forView(findViewById(R.id.btn), "Generate Password", "Click the button to generate your password.")
                                .outerCircleColor(R.color.ocean)
                                .targetCircleColor(android.R.color.white)
                                .textColor(android.R.color.white)
                                .cancelable(true)
                );
        sequence.start();
    }
}