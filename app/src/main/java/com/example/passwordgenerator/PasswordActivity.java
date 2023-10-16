package com.example.passwordgenerator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.content.Context;
import java.security.SecureRandom;
import android.content.ClipData;
import android.widget.Toast;



public class PasswordActivity extends AppCompatActivity {
    int len ;
    Boolean c1,c2,c3,c4;
    TextView tV;
    Button btnCopy;
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
        //c1 =true;c2=true;c3=true;c4=true;
        SharedPreferences pref = getSharedPreferences("main",MODE_PRIVATE);
        c1 = pref.getBoolean("c1",false);
        c2 = pref.getBoolean("c2",false);
        c3 = pref.getBoolean("c3",false);
        c4 = pref.getBoolean("c4",false);

        String pass = Generate(len,c1,c2,c3,c4);
        tV=findViewById(R.id.tv);
        btnCopy = findViewById(R.id.btnCopy);
        tV.setText(pass);

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipboard();
            }
        });

    }

    private String Generate(int len,Boolean c1,Boolean c2,Boolean c3,Boolean c4)
    {

        String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUMBERS = "012345679";
        String SPECIAL_SYMBOLS = "!@#$%^&*()-_=+[]{};:,.<>/?";
        String VALID_CHARACTERS="";
        if(c1==true) {
            VALID_CHARACTERS += UPPERCASE_CHARACTERS;
        }
        if(c2==true){
            VALID_CHARACTERS += LOWERCASE_CHARACTERS;
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


    private void copyToClipboard() {
        // Get the text from the TextView
        CharSequence textToCopy = tV.getText();

        // Get the ClipboardManager
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        // Create a ClipData object to hold the text
        ClipData clipData = ClipData.newPlainText("label", textToCopy);

        // Set the ClipData to the clipboard
        clipboardManager.setPrimaryClip(clipData);


        // Check if the device is running Android 12 or lower
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S) {
            // Display the toast only for Android 12 or lower
            Toast.makeText(this, "copied to clipboard", Toast.LENGTH_SHORT).show();
        }

    }
}
