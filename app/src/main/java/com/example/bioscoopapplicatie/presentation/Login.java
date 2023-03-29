package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.User;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Button log_in_btn;
    private TextView email_txtField;
    private TextView password_txtField;
    ///private UserViewModel userViewModel;
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
        this.email_txtField = findViewById(R.id.login_email_input_txt);
        this.password_txtField = findViewById(R.id.login_password_input_txt);
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
            setContentView(R.layout.login_horizontal);
        } else {
            setContentView(R.layout.login_vertical);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        /*
        switch (view.getId()) {
            case R.id.:
                    //
                    break;
        }*/
        //userViewModel.login(email_txtField.getText().toString(), password_txtField.getText().toString());
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}