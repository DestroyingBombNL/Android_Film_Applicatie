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
import android.widget.Toast;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListViewModel;

public class CreateMediaList extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView logo_img;
    private Button search_fld;
    private MediaListViewModel mediaListViewModel;
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

    void setComponents() {
        Log.i(TAG, "setComponents");
        this.logo_img = findViewById(R.id.logo_img);
        this.logo_img.setOnClickListener(this);
        this.search_fld = findViewById(R.id.search);
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

    void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListViewModel = new ViewModelProvider(this).get(MediaListViewModel.class);
    }

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //setContentView(R.layout.create_media_list_horizontal);
            setContentView(R.layout.create_media_list_vertical);
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
                Intent intentListView = new Intent(this, ShowMediaList.class);
                startActivity(intentListView);
                break;
            case R.id.create_media_list_create_list_btn:
                String name = String.valueOf(this.name_fld.getText());
                String description = String.valueOf(this.description_txt.getText());
                if (name.isEmpty()) {
                    Toast.makeText(this, "Please enter listname", Toast.LENGTH_SHORT).show();
                } else if (!name.isEmpty() && description.isEmpty()) {
                    MediaList mediaList = new MediaList(name,"No description");
                    this.mediaListViewModel.insertMediaList(mediaList);
                    Intent intentCreateNewList = new Intent(this, ShowMediaList.class);
                    startActivity(intentCreateNewList);
                } else {
                    MediaList mediaList = new MediaList(name,description);
                    this.mediaListViewModel.insertMediaList(mediaList);
                    Intent intentCreateNewList = new Intent(this, ShowMediaList.class);
                    startActivity(intentCreateNewList);
                }
                break;
            case R.id.logo_img:
                Intent intentHome = new Intent(this, Homescreen.class);
                startActivity(intentHome);
                break;
            case R.id.search:
                Intent intentSearch = new Intent(this, SearchMedia.class);
                startActivity(intentSearch);
                break;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
    }

    public String getTAG() {
        return TAG;
    }

    public ImageView getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(ImageView logo_img) {
        this.logo_img = logo_img;
    }

    public Button getSearch_fld() {
        return search_fld;
    }

    public void setSearch_fld(Button search_fld) {
        this.search_fld = search_fld;
    }

    public MediaListViewModel getMediaListViewModel() {
        return mediaListViewModel;
    }

    public void setMediaListViewModel(MediaListViewModel mediaListViewModel) {
        this.mediaListViewModel = mediaListViewModel;
    }

    public EditText getName_fld() {
        return name_fld;
    }

    public void setName_fld(EditText name_fld) {
        this.name_fld = name_fld;
    }

    public Button getCreate_btn() {
        return create_btn;
    }

    public void setCreate_btn(Button create_btn) {
        this.create_btn = create_btn;
    }

    public TextView getDescription_txt() {
        return description_txt;
    }

    public void setDescription_txt(TextView description_txt) {
        this.description_txt = description_txt;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public ImageButton getHomeScreenButton() {
        return homeScreenButton;
    }

    public void setHomeScreenButton(ImageButton homeScreenButton) {
        this.homeScreenButton = homeScreenButton;
    }

    public ImageButton getListAddButton() {
        return listAddButton;
    }

    public void setListAddButton(ImageButton listAddButton) {
        this.listAddButton = listAddButton;
    }

    public ImageButton getListViewButton() {
        return listViewButton;
    }

    public void setListViewButton(ImageButton listViewButton) {
        this.listViewButton = listViewButton;
    }
}