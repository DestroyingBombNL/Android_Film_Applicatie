package com.example.bioscoopapplicatie.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName="media_table")
public class Media {
    @Expose
    private boolean adult;
    @Expose
    @ColumnInfo(name="backdrop_path")
    private String backdropPath;
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Expose
    @ColumnInfo(name="original_language")
    private String language;
    @Expose
    @ColumnInfo(name="original_title")
    private String title;
    @Expose
    private String overview;
    @Expose
    private double popularity;
    @Expose
    @ColumnInfo(name="poster_path")
    private String posterPath;
    @Expose
    @ColumnInfo(name="release_date")
    private String releaseDate;
    @Expose
    private boolean video;
    @Expose
    @ColumnInfo(name="vote_average")
    private double voteAverage;
    @Expose
    @ColumnInfo(name="vote_count")
    private int voteCount;

    public Media(int id, String title, String language, String overview, double popularity, String releaseDate, boolean adult, String backdropPath, String posterPath, boolean video, double voteAverage, int voteCount) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    /*tweede constructor met velden die ingevuld moeten worden bij aanmaken van film
    @Ignore
    public Media(String title, String language, String overview, String releaseDate, boolean adult, String backdropPath, String posterPath, boolean video) {
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.video = video;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
