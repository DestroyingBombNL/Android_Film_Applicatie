package com.example.bioscoopapplicatie.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.DataOrder;
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.adapter.GenreSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.OrderSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.GenreViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListMediaViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ShowMediaListDetails extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private Button searchButton;
    private int orientation;
    private int columnCount;
    private MediaListMediaViewModel mediaListMediaViewModel;
    private FloatingActionButton floatingActionButton;
    private ImageButton homeScreenButton, listAddButton, listViewButton;
    private MediaList mediaList;
    private TextView name_and_number_txt;
    private int listNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        readData();
        setRecyclerView();
        setViewModel();
        setComponents();
    }

    private void readData() {
        Log.i(TAG, "readData");
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.mediaList = new MediaList(extra.getInt("id"),
                    extra.getString("name"),
                    extra.getString("description"),
                    extra.getInt("favoriteCount"));
            this.listNumber = extra.getInt("listNumber");
        }
    }

    private void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.show_media_list_details_recycler_view);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaListMediaViewModel = new ViewModelProvider(this).get(MediaListMediaViewModel.class);
        mediaListMediaViewModel.getAllMediaInList(String.valueOf(this.mediaList.getId())).observe(this, new Observer<List<Media>>() {
            @Override
            public void onChanged(List<Media> media) {
                adapter.setData((ArrayList<Media>) media);
                Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG);
            }
        });
    }

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.columnCount = 2;
            //setContentView(R.layout.show_media_list_details_horizontal);
            setContentView(R.layout.show_media_list_details);
        } else {
            this.columnCount = 1;
            setContentView(R.layout.show_media_list_details);
        }
    }

    public void setComponents(){
        this.name_and_number_txt = findViewById(R.id.show_media_list_details_position_and_name_txt);

        //Homepage footer
        this.homeScreenButton = findViewById(R.id.homeScreenButton);
        this.homeScreenButton.setOnClickListener(this);

        //List_add footer
        this.listAddButton = findViewById(R.id.listAddButton);
        this.listAddButton.setOnClickListener(this);

        //List button footer
        this.listViewButton = findViewById(R.id.listViewButton);
        this.listViewButton.setOnClickListener(this);

        this.name_and_number_txt.setText("List: " + this.listNumber + " - Created by " + this.mediaList.getName());
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick aangeroepen");
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchMedia.class);
                startActivity(intent);
                break;

            case R.id.homeScreenButton:
                Intent intentHome = new Intent(this, Homescreen.class);
                startActivity(intentHome);
                break;

            case R.id.listAddButton:
                Intent intentListAdd = new Intent(this, CreateMediaList.class);
                startActivity(intentListAdd);
                break;

            case R.id.listViewButton:
                Intent intentListView = new Intent(this, ShowMediaList.class);
                startActivity(intentListView);
                break;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Check the orientation and update the number of columns in the layout manager accordingly
        setLayoutBasedOnOrientation();
        setRecyclerView();
        setViewModel();
        setComponents();
    }
}