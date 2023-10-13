package com.example.passwordgenerator;



import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {

    private PasswordGenerator passwordGenerator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        passwordGenerator = new PasswordGenerator();

        EditText passwordLengthInput = findViewById(R.id.Password_Length);
        Button generateButton = findViewById(R.id.generate);
        TextView passwordOutput= findViewById(R.id.newPassword);

        generateButton.setOnClickListener(v -> {
    @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Lowercase = findViewById(R.id.LowerCase);
    @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Uppercase = findViewById(R.id.Uppercase);
    @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Number= findViewById(R.id.Numbers);
    @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Special = findViewById(R.id.special);

    if( (passwordLengthInput.getText().length()) <= 0) {
        return;
    }
    boolean anyToggleSelected =Lowercase.isChecked() ||
            Uppercase.isSelected() ||
            Number.isSelected() ||
            Special.isSelected();


    int passwordLength = Integer.parseInt(String.valueOf(passwordLengthInput.getText()));
    if(anyToggleSelected){
        String generatedPassword = passwordGenerator.generatePassword(passwordLength,
                Uppercase.isSelected() ,
                Lowercase.isChecked(),
                Number.isSelected(),
                Special.isSelected());

        passwordOutput.setText(generatedPassword);}


        });
    }
}

