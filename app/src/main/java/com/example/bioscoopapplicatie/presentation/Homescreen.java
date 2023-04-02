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

import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.DataOrder;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.presentation.adapter.GenreSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.OrderSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Homescreen extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private MediaViewModel mediaViewModel;
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private Button searchButton;
    private int orientation;
    private int columnCount;
    private Spinner spinner_genre;
    private GenreSpinnerAdapter genreAdapter;
    private Spinner spinner_order;
    private OrderSpinnerAdapter orderAdapter;

    private ImageButton homeScreenButton, listAddButton, listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setViews();
        setRecyclerView();
        setViewModel();
        setSpinner();

    }


    private void setSpinner(){
        Log.i(TAG, "setSpinners");
        //Genre spinner
        this.spinner_genre = findViewById(R.id.homescreen_genre_spn);
//        genreAdapter = new GenreSpinnerAdapter(Homescreen.this, DataGenre.getGenreList());
        spinner_genre.setAdapter(genreAdapter);
        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "Selected genre: " + selectedGenre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Order spinner
        this.spinner_order = findViewById(R.id.homescreen_miscellaneous_spn);
        orderAdapter = new OrderSpinnerAdapter(Homescreen.this, DataOrder.getOrderList());
        spinner_order.setAdapter(orderAdapter);
        spinner_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOrder = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "Selected order: " + selectedOrder);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void setViews() {
        Log.i(TAG, "setViews");
        this.searchButton = findViewById(R.id.search);
        this.searchButton.setOnClickListener(this);
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
    public void onClick(View v) {
        Log.d(TAG, "onClick aangeroepen");
        switch (v.getId()) {
            case R.id.search:
                Intent intent = new Intent(this, SearchMedia.class);
                startActivity(intent);
                break;

            case R.id.homeScreenButton:
                Log.d(TAG, "Button aangeroepen");
                Intent intentHome = new Intent(this, Homescreen.class);
                startActivity(intentHome);
                break;

            case R.id.listAddButton:
                Log.d(TAG, "Button aangeroepen");
                Intent intentListAdd = new Intent(this, CreateList.class);
                startActivity(intentListAdd);
                break;

            case R.id.listViewButton:
                Log.d(TAG, "Button aangeroepen");
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
        setViews();
        setRecyclerView();
        setViewModel();
    }
}