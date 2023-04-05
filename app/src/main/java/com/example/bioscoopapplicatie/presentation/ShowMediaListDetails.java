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
    private Spinner spinner_genre;
    private GenreSpinnerAdapter genreAdapter;
    private GenreViewModel genreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        readData();
        setRecyclerView();
        setViewModel();
        setComponents();
        setSpinner();
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

    void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.show_media_list_details_recycler_view);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    void setViewModel() {
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

    void setLayoutBasedOnOrientation() {
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

        //Fab
        this.floatingActionButton = findViewById(R.id.floatingActionButton);
        this.floatingActionButton.setOnClickListener(this);
    }

    private void setSpinner(){
        Log.i(TAG, "setSpinners");
        // Check if the intent has extra data
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.mediaList = new MediaList(extra.getInt("id"),
                    extra.getString("name"),
                    extra.getString("description"),
                    extra.getInt("favoriteCount"));
            this.listNumber = extra.getInt("listNumber");
        }

        //Genre spinner
        this.spinner_genre = findViewById(R.id.list_genre_spn);
        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        genreViewModel.getAllGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                genres.add(0, new Genre(0, "All"));
                spinner_genre.setSelection(0);
                genreAdapter = new GenreSpinnerAdapter(ShowMediaListDetails.this, genres);
                spinner_genre.setAdapter(genreAdapter);

            }
        });
        spinner_genre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Genre selectedGenre = (Genre) genreAdapter.getItem(position);
                Log.i(TAG, "onItemSelected: " + selectedGenre.getName());
                if(position == 0){
                    mediaListMediaViewModel.getAllMediaInList(String.valueOf(mediaList.getId())).observe(ShowMediaListDetails.this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                        }
                    });
                } else {
                    mediaListMediaViewModel.getAllFilteredMediaListByGenre(selectedGenre.getId()).observe(ShowMediaListDetails.this, new Observer<List<Media>>() {
                        @Override
                        public void onChanged(List<Media> media) {
                            adapter.setData((ArrayList<Media>) media);
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

            case R.id.floatingActionButton:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                String title = "Sharing media info!";
                String text = "The media is called: " + mediaList.getName() + "\n" +
                        "This is what it is about: " + mediaList.getDescription();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
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

    public String getTAG() {
        return TAG;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public HomescreenAdapter getAdapter() {
        return adapter;
    }

    public GridLayoutManager getmLayoutManager() {
        return mLayoutManager;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public int getOrientation() {
        return orientation;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public MediaListMediaViewModel getMediaListMediaViewModel() {
        return mediaListMediaViewModel;
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }

    public ImageButton getHomeScreenButton() {
        return homeScreenButton;
    }

    public ImageButton getListAddButton() {
        return listAddButton;
    }

    public ImageButton getListViewButton() {
        return listViewButton;
    }

    public MediaList getMediaList() {
        return mediaList;
    }

    public TextView getName_and_number_txt() {
        return name_and_number_txt;
    }

    public int getListNumber() {
        return listNumber;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setAdapter(HomescreenAdapter adapter) {
        this.adapter = adapter;
    }

    public void setmLayoutManager(GridLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setMediaListMediaViewModel(MediaListMediaViewModel mediaListMediaViewModel) {
        this.mediaListMediaViewModel = mediaListMediaViewModel;
    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }

    public void setHomeScreenButton(ImageButton homeScreenButton) {
        this.homeScreenButton = homeScreenButton;
    }

    public void setListAddButton(ImageButton listAddButton) {
        this.listAddButton = listAddButton;
    }

    public void setListViewButton(ImageButton listViewButton) {
        this.listViewButton = listViewButton;
    }

    public void setMediaList(MediaList mediaList) {
        this.mediaList = mediaList;
    }

    public void setName_and_number_txt(TextView name_and_number_txt) {
        this.name_and_number_txt = name_and_number_txt;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }
}