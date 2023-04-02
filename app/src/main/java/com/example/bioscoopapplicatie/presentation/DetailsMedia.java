package com.example.bioscoopapplicatie.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.presentation.adapter.HomescreenAdapter;

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
    private Button to_list_btn;
    private TextView description_txt;
    private RecyclerView recyclerView;
    private HomescreenAdapter adapter;
    private GridLayoutManager mLayoutManager;
    private int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setLayoutBasedOnOrientation();
        readData();
        setComponents();
        setRecyclerView();
        putDataInComponents();
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

    private void setComponents() {
        Log.i(TAG, "setComponents");
        this.media_img = findViewById(R.id.details_media_img);
        this.title_txt = findViewById(R.id.details_media_title_txt);
        this.popularity_txt = findViewById(R.id.details_media_popularity_txt);
        this.adult_txt = findViewById(R.id.details_media_adult_txt);
        this.date_txt = findViewById(R.id.details_media_date_txt);
        this.language_txt = findViewById(R.id.details_media_language_txt);
        this.description_txt = findViewById(R.id.details_media_description_txt);
        this.share_btn = findViewById(R.id.details_media_share_btn);
        this.to_list_btn = findViewById(R.id.details_media_to_list_btn);
        this.share_btn.setOnClickListener(this);
        this.to_list_btn.setOnClickListener(this);
    }

    private void setRecyclerView() {
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

    private void setLayoutBasedOnOrientation() {
        Log.i(TAG, "setLayoutBasedOnOrientation");
        this.orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.details_media_horizontal);
        } else {
            setContentView(R.layout.details_media_vertical);
        }
    }

    @Override
    public void onClick(View v) {

        Log.d(TAG, "onClick");
        Button shareButton = findViewById(R.id.details_media_share_btn);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        String title = "Sharing media info!";
        String text = "It's called: " + media.getTitle() + "\n" +
                      "This is what it is about: " + media.getOverview();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        setLayoutBasedOnOrientation();
        putDataInComponents();
    }
}