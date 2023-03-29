package com.example.bioscoopapplicatie.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bioscoopapplicatie.R;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private Button register_btn;
    private CheckBox terms_cbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_vertical);
        setComponents();
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.terms_cbx = findViewById(R.id.terms_cbx);
        this.register_btn = findViewById(R.id.register_btn);
        this.register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                if(terms_cbx.isChecked()){
                    startActivity(loginIntent);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Must agree to terms and conditions", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
