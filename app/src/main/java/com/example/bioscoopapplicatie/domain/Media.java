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
    private String backdrop_path;
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Expose
    @ColumnInfo(name="original_language")
    private String original_language;
    @Expose
    @ColumnInfo(name="original_title")
    private String title;
    @Expose
    private String overview;
    @Expose
    private double popularity;
    @Expose
    @ColumnInfo(name="poster_path")
    private String poster_path;
    @Expose
    @ColumnInfo(name="release_date")
    private String release_date;
    @Expose
    private boolean video;
    @Expose
    @ColumnInfo(name="vote_average")
    private double vote_average;
    @Expose
    @ColumnInfo(name="vote_count")
    private int vote_count;

    public Media(int id, String title, String original_language, String overview, double popularity, String release_date, boolean adult, String backdrop_path, String poster_path, boolean video, double vote_average, int vote_count) {
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.overview = overview;
        this.popularity = popularity;
        this.release_date = release_date;
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    /*tweede constructor met velden die ingevuld moeten worden bij aanmaken van film
    @Ignore
    public Media(String title, String language, String overview, String releaseDate, boolean adult, String backdropPath, String poster_path, boolean video) {
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.poster_path = poster_path;
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

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
