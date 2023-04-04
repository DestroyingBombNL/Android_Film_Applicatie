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
import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.presentation.adapter.GenreSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.OrderSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.GenreViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    int orientation;
    int columnCount;
    private Spinner spinner_genre;
    private GenreSpinnerAdapter genreAdapter;
    private Spinner spinner_order;
    private OrderSpinnerAdapter orderAdapter;
    FloatingActionButton floatingActionButton;

    private ImageButton homeScreenButton, listAddButton, listViewButton;
    private MediaList mediaList;
    private GenreViewModel genreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        setViews();
        setRecyclerView();
        setViewModel();
        setSpinner();
        setComponents();

    }


    private void setSpinner(){
        Log.i(TAG, "setSpinners");
        //Genre spinner
        this.spinner_genre = findViewById(R.id.homescreen_genre_spn);
        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        genreViewModel.getAllGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                genreAdapter = new GenreSpinnerAdapter(Homescreen.this, genres);
                spinner_genre.setAdapter(genreAdapter);
            }
        });
        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedGenreId = (Integer) parent.getItemAtPosition(position);
                Log.d(TAG, "Selected genre id: " + selectedGenreId);
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
                String selectedOrder = spinner_order.getSelectedItem().toString();

                Log.d(TAG, "Selected order: " + selectedOrder);

                if(selectedOrder.equals("0")) {
                    mediaViewModel.getAllMedia().observe(Homescreen.this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                            Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG);
                        }
                    });
                } else if (selectedOrder.equals("1")) {
                    mediaViewModel.getAllOrderedVoteAverageMedia().observe(Homescreen.this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                            Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG);
                        }
                    });
                } else if (selectedOrder.equals("2")) {
                    mediaViewModel.getAllOrderedReleaseDateMedia().observe(Homescreen.this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                            Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG);
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mediaViewModel.getAllMedia().observe(Homescreen.this, new Observer<List<Media>>() {
                    @Override
                    public void onChanged(List<Media> media) {
                        adapter.setData((ArrayList<Media>) media);
                        Snackbar.make(recyclerView, String.valueOf(media.size() + " Media read"), Snackbar.LENGTH_LONG);
                    }
                });
            }
        });
    }


    void setViews() {
        Log.i(TAG, "setViews");
        this.searchButton = findViewById(R.id.search);
        this.searchButton.setOnClickListener(this);
    }

    void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.homescreen_recycler);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    void setViewModel() {
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

    void setLayoutBasedOnOrientation() {
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

    public void setComponents(){
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
                Intent intentListAdd = new Intent(this, CreateMediaList.class);
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

    public MediaViewModel getMediaViewModel() {
        return mediaViewModel;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public HomescreenAdapter getAdapter() {
        return adapter;
    }

    public Spinner getSpinner_genre() {
        return spinner_genre;
    }

    public GenreSpinnerAdapter getGenreAdapter() {
        return genreAdapter;
    }

    public Spinner getSpinner_order() {
        return spinner_order;
    }

    public OrderSpinnerAdapter getOrderAdapter() {
        return orderAdapter;
    }

    public MediaList getMediaList() {
        return mediaList;
    }

    public GenreViewModel getGenreViewModel() {
        return genreViewModel;
    }
}