package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bioscoopapplicatie.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Button log_in_btn;
    private Button register_btn_act;
    private TextView forgot_password_txt;
    private TextView email_txtField;
    private TextView password_txtField;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setComponents();
        setUserViewModel();
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.log_in_btn = findViewById(R.id.login_btn);
        this.log_in_btn.setOnClickListener(this);
        this.register_btn_act = findViewById(R.id.register_btn);
        this.register_btn_act.setOnClickListener(this);
        this.forgot_password_txt = findViewById(R.id.forgot_password_txt);
        this.forgot_password_txt.setOnClickListener(this);
    }

    private void setUserViewModel() {
        Log.i(TAG, "setUserViewModel");
        /*
        ViewModelProvider provider = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()));
        userViewModel = provider.get(UserViewModel.class);
        userViewModel.getUserData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                String message = "User Changed to  " + user.getFirstName();
                Log.d(TAG, message);
                Log.d(TAG, "user.token = " + user.getToken());
                // TODO : initiate successful logged in experience
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.login_vertical); // change to horizontal
        } else {
            setContentView(R.layout.login_vertical);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");

        switch (view.getId()) {
            case R.id.login_btn:
                Intent homescreenIntent = new Intent(this, Homescreen.class);
                startActivity(homescreenIntent);
                break;
            case R.id.register_btn:
                Intent registerIntent = new Intent(this, Register.class);
                startActivity(registerIntent);
                break;
            case R.id.forgot_password_txt:
                Intent forgotPasswordIntent = new Intent(Login.this, ForgotPassword.class);
                startActivity(forgotPasswordIntent);
                break;
        }
        //userViewModel.login(email_txtField.getText().toString(), password_txtField.getText().toString());
        //Intent intent = new Intent(this, Homepage.class);
        //startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}