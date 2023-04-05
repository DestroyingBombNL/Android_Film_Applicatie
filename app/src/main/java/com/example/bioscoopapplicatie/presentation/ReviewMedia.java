package com.example.bioscoopapplicatie.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bioscoopapplicatie.R;
import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.Review;
import com.example.bioscoopapplicatie.presentation.viewmodel.AuthorDetailViewModel;
import com.example.bioscoopapplicatie.presentation.viewmodel.ReviewViewModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReviewMedia extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Media media;
    private TextView title_txt;
    private ImageView img;
    private RatingBar bar;
    private TextView description_txt;
    private ReviewViewModel reviewViewModel;
    private AuthorDetailViewModel authorDetailViewModel;
    private Button send_review_btn;
    private ImageButton homeScreenButton, listAddButton, listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_vertical);
        readData();
        setViews();
        setData();
        setViewModels();
    }

    void setViews() {
        Log.i(TAG, "setViews");
        this.title_txt = findViewById(R.id.review_title_txt);
        this.img = findViewById(R.id.review_img);
        this.bar = findViewById(R.id.review_rating_bar);
        this.description_txt = findViewById(R.id.review_description_txt);
        this.send_review_btn = findViewById(R.id.review_btn);
        this.send_review_btn.setOnClickListener(this);
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

    private void setData() {
        this.title_txt.setText(media.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/original" + this.media.getPosterPath()).into(this.img);
    }

    private void setViewModels() {
        this.reviewViewModel = new ReviewViewModel(getApplication());
        this.authorDetailViewModel = new AuthorDetailViewModel(getApplication());
    }

    @Override
    public void onClick(View v) {
        String uniqueId = "DestroyingBombNLsReview: " + this.description_txt.getText();
        AuthorDetail newAuthorDetail = new AuthorDetail("", "DestroyingBombNL", "", (double) this.bar.getRating());
        Review newReview = new Review("DestroyingBombNL", newAuthorDetail, String.valueOf(this.description_txt.getText()), String.valueOf(java.time.LocalDateTime.now()), uniqueId, this.media.getId());
        this.reviewViewModel.insertReview(newReview);
        this.authorDetailViewModel.insertAuthorDetail(newReview.getAuthorDetails());
        finish();
    }
}