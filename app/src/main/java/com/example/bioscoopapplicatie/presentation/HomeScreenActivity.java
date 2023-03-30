package com.example.bioscoopapplicatie.presentation;

import androidx.annotation.Nullable;
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
import android.widget.Button;
import android.widget.ImageView;

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private MediaViewModel mediaViewModel;
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private int orientation;
    private int columnCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setViews();
        setRecyclerView();
        setViewModel();
    }

    private void setViews() {
        Log.i(TAG, "setViews");
    }
    private void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.homescreen_recycler);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setViewModel() {
        Log.i(TAG, "setViewModel");
        this.mediaViewModel = new ViewModelProvider(this).get(MediaViewModel.class);
        mediaViewModel.getAllMedia().observe(this, new Observer<List<Media>>() {
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
            //setContentView(R.layout.homescreen_horizontal);
            setContentView(R.layout.homescreen_vertical);
        } else {
            this.columnCount = 1;
            setContentView(R.layout.homescreen_vertical);
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
}