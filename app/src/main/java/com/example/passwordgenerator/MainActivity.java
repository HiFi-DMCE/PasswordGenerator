


// new merged

package com.example.passwordgenerator;

      

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import android.annotation.SuppressLint;

        import android.widget.EditText;
        import android.widget.Switch;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PasswordActivity passwordGenerator = new PasswordActivity();

        EditText passwordLengthInput = findViewById(R.id.Password_Length);

        Button btn;
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Lowercase = findViewById(R.id.LowerCase);
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Uppercase = findViewById(R.id.Uppercase);
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Number= findViewById(R.id.Numbers);
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch Special = findViewById(R.id.special);
                if( (passwordLengthInput.getText().length()) <= 0) {
                    return;
                }
                boolean anyToggleSelected =Lowercase.isChecked() ||
                        Uppercase.isChecked() ||
                        Number.isChecked() ||
                        Special.isChecked();


                int passwordLength = Integer.parseInt(String.valueOf(passwordLengthInput.getText()));
                if(anyToggleSelected){
                     passwordGenerator.generatePassword(passwordLength,
                            Uppercase.isChecked() ,
                            Lowercase.isChecked(),
                            Number.isChecked(),
                            Special.isChecked());

                Toast.makeText(MainActivity.this, "Password Generated Successfully", Toast.LENGTH_SHORT).show();
                    Intent iBtn = new Intent (MainActivity.this, PasswordActivity.class);
                    startActivity(iBtn);

            }
        }); // TODO check for syntax/braces errors
    }
    }

