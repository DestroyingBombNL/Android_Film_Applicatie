package com.example.bioscoopapplicatie.domain;

public class Media {
    private int id;
    private String title;
    private String language;
    private String overview;
    private double popularity;
    private String releaseDate;
    private boolean adult;
    private String backdropPath;
    private String posterPath;
    private boolean video;
    private double voteAverage;
    private int voteCount;
    private int season;

    public Media(int id, String title, String language, String overview, double popularity, String releaseDate, boolean adult, String backdropPath, String posterPath, boolean video, double voteAverage, int voteCount, int season) {
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
        this.season = season;
    }

    //tweede constructor met velden die ingevuld moeten worden bij aanmaken van film
    public Media(String title, String language, String overview, String releaseDate, boolean adult, String backdropPath, String posterPath, boolean video, int season) {
        this.title = title;
        this.language = language;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.video = video;
        this.season = season;
    }

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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
