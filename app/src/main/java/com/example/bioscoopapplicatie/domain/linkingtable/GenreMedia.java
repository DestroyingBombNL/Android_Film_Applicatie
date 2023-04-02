package com.example.bioscoopapplicatie.domain.linkingtable;

import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;

public class GenreMedia {
    private Genre genre;
    private Media media;

    public GenreMedia(Genre genre, Media media) {
        this.genre = genre;
        this.media = media;
    }
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

}
