package com.example.bioscoopapplicatie.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.adapter.GenreSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.OrderSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.GenreViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SearchMedia extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private MediaViewModel mediaViewModel;
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private EditText searchInput;
    private int orientation;
    private int columnCount;
    private ImageButton homeScreenButton, listAddButton, listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "searchActivity");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setViews();
        setComponents();
        setRecyclerView();
        setViewModel();
    }

    void setViews() {
        Log.i(TAG, "setViews");
        this.searchInput = findViewById(R.id.search_bar);
        Button searchButton = findViewById(R.id.search_media);
        searchButton.setOnClickListener(this);
//        this.searchInput.setOnClickListener(this);
    }

    void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.search_recycler_view);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaViewModel = new ViewModelProvider(this).get(MediaViewModel.class);
        mediaViewModel.getAllMedia().observe(this, new Observer<List<Media>>() {
            @Override
            public void onChanged(List<Media> media) {
                // Check if user has entered any search query
                adapter.setData((ArrayList<Media>) media);
                Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.columnCount = 2;
            //setContentView(R.layout.search_horizontal);
            setContentView(R.layout.search_vertical);
        } else {
            this.columnCount = 1;
            setContentView(R.layout.search_vertical);
        }
    }

    public void setComponents() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.search_media:
                String query = searchInput.getText().toString().trim();
                if (!query.isEmpty()) {
                    mediaViewModel.getFilteredMedia(query).observe(this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                            Log.d(TAG, "onChanged: " + query);
                        }
                    });
                } else {
                    mediaViewModel.getAllMedia().observe(this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                            Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Check the orientation and update the number of columns in the layout manager accordingly
        setLayoutBasedOnOrientation();
        setViews();
        setRecyclerView();
        setViewModel();
    }

    public MediaViewModel getMediaViewModel() {
        return mediaViewModel;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public HomescreenAdapter getAdapter() {
        return adapter;
    }

    public LinearLayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getColumnCount() {
        return columnCount;
    }
}