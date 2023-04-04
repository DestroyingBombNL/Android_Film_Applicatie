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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;
import com.example.bioscoopapplicatie.presentation.adapter.ToListSpinnerAdapter;
import com.example.bioscoopapplicatie.presentation.viewmodel.GenreViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.MediaListViewModel;

import java.util.List;

public class DetailsMedia extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Media media;
    private ImageView media_img;
    private TextView title_txt;
    private TextView popularity_txt;
    private TextView adult_txt;
    private TextView date_txt;
    private TextView language_txt;
    private Button share_btn;
    private TextView description_txt;
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private ToListSpinnerAdapter toListSpinnerAdapter;
    private Spinner toListSpinner;
    private int orientation;
    private ImageButton homeScreenButton, listAddButton, listViewButton;
    private Button shareButton;
    private MediaListViewModel mediaListViewModel;
    private MediaList mediaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        readData();
        setComponents();
        setRecyclerView();
        setSpinner();
        putDataInComponents();
    }

    private void setSpinner() {
        Log.i(TAG, "setSpinner");
        this.toListSpinner = findViewById(R.id.details_media_to_list_spinner);
        mediaListViewModel = new ViewModelProvider(this).get(MediaListViewModel.class);
        mediaListViewModel.getAllMediaLists().observe(this, new Observer<List<MediaList>>() {
            @Override
            public void onChanged(List<MediaList> mediaLists) {
                // Add an empty option at the beginning of the list
                MediaList emptyOption = new MediaList();
                mediaLists.add(0, emptyOption);
                // Set up the spinner adapter
                toListSpinnerAdapter = new ToListSpinnerAdapter(DetailsMedia.this, mediaLists);
                toListSpinner.setAdapter(toListSpinnerAdapter);
            }
        });

        toListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MediaList mediaList = (MediaList) toListSpinnerAdapter.getItem(position);
                // Ignore the selection if the empty option is selected
                if (position == 0) {
                    return;
                }
                Log.i(TAG, "onItemSelected: " + mediaList.getName());
                // Add media to list selected in spinner
                mediaListViewModel.insertMediaToList(new MediaListMedia(mediaList.getId(), media.getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "Nothing selected");
            }
        });
    }
    private void readData() {
        Log.i(TAG, "readData");
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            this.media = new Media(extra.getInt("id"),
                    extra.getString("title"),
                    extra.getString("language"),
                    extra.getString("overview"),
                    extra.getDouble("popularity"),
                    extra.getString("releaseDate"),
                    extra.getBoolean("adult"),
                    extra.getString("backdropPath"),
                    extra.getString("posterPath"),
                    extra.getBoolean("video"),
                    extra.getDouble("voteAverage"),
                    extra.getInt("voteCount"));
        }
    }

    void setComponents() {
        Log.i(TAG, "setComponents");
        this.media_img = findViewById(R.id.details_media_img);
        this.title_txt = findViewById(R.id.details_media_title_txt);
        this.popularity_txt = findViewById(R.id.details_media_popularity_txt);
        this.adult_txt = findViewById(R.id.details_media_adult_txt);
        this.date_txt = findViewById(R.id.details_media_date_txt);
        this.language_txt = findViewById(R.id.details_media_language_txt);
        this.description_txt = findViewById(R.id.details_media_description_txt);
        this.share_btn = findViewById(R.id.details_media_share_btn);
        this.share_btn.setOnClickListener(this);


        this.homeScreenButton = findViewById(R.id.homeScreenButton);
        this.homeScreenButton.setOnClickListener(this);

        //List_add footer
        this.listAddButton = findViewById(R.id.listAddButton);
        this.listAddButton.setOnClickListener(this);

        //List button footer
        this.listViewButton = findViewById(R.id.listViewButton);
        this.listViewButton.setOnClickListener(this);

        //Share button
        this.shareButton = findViewById(R.id.details_media_share_btn);
        this.shareButton.setOnClickListener(this);

        //Spinner
        this.toListSpinner = findViewById(R.id.details_media_to_list_spinner);
    }

    void setRecyclerView() {
        Log.i(TAG, "setRecyclerView");
        recyclerView = findViewById(R.id.details_media_recycler);
        adapter = new HomescreenAdapter(this);
        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void putDataInComponents() {
        Log.i(TAG, "putDataInComponents");
        Glide.with(this).load("https://image.tmdb.org/t/p/original" + this.media.getPosterPath()).into(this.media_img);
        this.title_txt.setText(media.getTitle());
        this.popularity_txt.setText(String.valueOf(media.getPopularity()));
        this.adult_txt.setText(String.valueOf(media.isAdult()));
        this.date_txt.setText(media.getReleaseDate());
        this.language_txt.setText(media.getOriginalLanguage());
        this.description_txt.setText(media.getOverview());
    }

    void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.details_media_horizontal);
        } else {
            setContentView(R.layout.details_media_vertical);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_media_share_btn:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                String title = "Sharing media info!";
                String text = "The media is called: " + media.getTitle() + "\n" +
                        "This is what it is about: " + media.getOverview() + "\n" +
                        "People give this media a rating of " + media.getVoteAverage() + "!";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;
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
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
        putDataInComponents();
    }

    public Media getMedia() {
        return media;
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

    public int getOrientation() {
        return orientation;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setMedia_img(ImageView media_img) {
        this.media_img = media_img;
    }

    public void setTitle_txt(TextView title_txt) {
        this.title_txt = title_txt;
    }

    public void setPopularity_txt(TextView popularity_txt) {
        this.popularity_txt = popularity_txt;
    }

    public void setAdult_txt(TextView adult_txt) {
        this.adult_txt = adult_txt;
    }

    public void setDate_txt(TextView date_txt) {
        this.date_txt = date_txt;
    }

    public void setLanguage_txt(TextView language_txt) {
        this.language_txt = language_txt;
    }

    public void setShare_btn(Button share_btn) {
        this.share_btn = share_btn;
    }

    public void setDescription_txt(TextView description_txt) {
        this.description_txt = description_txt;
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

    public void setOrientation(int orientation) {
        this.orientation = orientation;
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

    public String getTAG() {
        return TAG;
    }

    public ImageView getMedia_img() {
        return media_img;
    }

    public TextView getTitle_txt() {
        return title_txt;
    }

    public TextView getPopularity_txt() {
        return popularity_txt;
    }

    public TextView getAdult_txt() {
        return adult_txt;
    }

    public TextView getDate_txt() {
        return date_txt;
    }

    public TextView getLanguage_txt() {
        return language_txt;
    }

    public Button getShare_btn() {
        return share_btn;
    }

    public TextView getDescription_txt() {
        return description_txt;
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
}