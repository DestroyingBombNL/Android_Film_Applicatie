package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListViewModel;

public class CreateMediaList extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView logo_img;
    private EditText search_fld;
    private MediaListViewModel mediaListViewModel;

    /*private Button bar_home_btn;
    private Button bar_create_media_list_btn;
    private Button bar_show_media_list_btn;*/
    private EditText name_fld;
    private Button create_btn;
    private TextView description_txt;
    private int orientation;
    private ImageButton homeScreenButton, listAddButton, listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setComponents();
        setViewModel();
    }

    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.logo_img = findViewById(R.id.logo_img);
        this.logo_img.setOnClickListener(this);
        this.search_fld = findViewById(R.id.search_fld);
        this.search_fld.setOnClickListener(this);
        this.create_btn = findViewById(R.id.create_media_list_create_list_btn);
        this.create_btn.setOnClickListener(this);
        this.name_fld = findViewById(R.id.create_media_list_name_fld);
        this.description_txt = findViewById(R.id.create_media_list_description_txt);

        //Homepage footer
        this.homeScreenButton = findViewById(R.id.homeScreenButton);
        this.homeScreenButton.setOnClickListener(this);

        //List_add footer
        this.listAddButton = findViewById(R.id.listAddButton);
        this.listAddButton.setOnClickListener(this);

        //List button footer
        this.listViewButton = findViewById(R.id.listViewButton);
        this.listViewButton.setOnClickListener(this);
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListViewModel = new ViewModelProvider(this).get(MediaListViewModel.class);
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
            case R.id.homeScreenButton:
                Intent intent = new Intent(this, Homescreen.class);
                startActivity(intent);
                break;
            case R.id.listAddButton:
                Intent intentAdd = new Intent(this, CreateMediaList.class);
                startActivity(intentAdd);
                break;
            case R.id.listViewButton:
                Log.d(TAG, "Button aangeroepen");
                Intent intentListView = new Intent(this, ShowMediaList.class);
                startActivity(intentListView);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }
}