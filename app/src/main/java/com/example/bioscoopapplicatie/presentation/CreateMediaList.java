package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bioscoopapplicatie.R;

public class CreateMediaList extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView logo_img;
    private EditText search_fld;
    /*private Button bar_home_btn;
    private Button bar_create_media_list_btn;
    private Button bar_show_media_list_btn;*/
    private EditText create_media_list_fld;
    private Button create_media_list_btn;
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
        this.logo_img = findViewById(R.id.logo_img);
        this.logo_img.setOnClickListener(this);
        this.search_fld = findViewById(R.id.search_fld);
        this.search_fld.setOnClickListener(this);
        this.create_media_list_btn = findViewById(R.id.create_media_list_create_list_btn);
        this.create_media_list_btn.setOnClickListener(this);
        this.create_media_list_fld = findViewById(R.id.create_media_list_name_fld);
        /*
        this.bar_home_btn = findViewById(R.id.create_media_list_bar_home_btn);
        this.bar_home_btn.setOnClickListener(this);
        this.bar_create_media_list_btn = findViewById(R.id.create_media_list_bar_btn);
        this.bar_create_media_list_btn.setOnClickListener(this);
        this.bar_show_media_list_btn = findViewById(R.id.create_media_list_bar_show_media_list_btn);
        */
    }

    private void setUserViewModel() {
        /*
        Log.i(TAG, "setUserViewModel");
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
            //setContentView(R.layout.create_media_list_horizontal);
        } else {
            setContentView(R.layout.create_media_list_vertical);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");
        switch (view.getId()) {
            case R.id.logo_img:
                /*Intent intent = new Intent(this, Homescreen.class);
                startActivity(intent);*/
                break;
            case R.id.search_fld:
                /*
                Intent intent = new Intent(this, SearchMedia.class);
                startActivity(intent);*/
                break;
            case R.id.create_media_list_create_list_btn:
                Intent intent = new Intent(this, ShowMediaList.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}