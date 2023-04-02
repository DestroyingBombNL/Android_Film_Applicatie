package com.example.bioscoopapplicatie.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bioscoopapplicatie.R;

public class ForgotPassword extends AppCompatActivity {
    private Button send_email_btn;
    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_vertical);
        setComponents();
    }
    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.send_email_btn = findViewById(R.id.send_email_btn);
        this.send_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });
    }

}
