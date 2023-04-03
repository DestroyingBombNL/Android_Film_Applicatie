package com.example.bioscoopapplicatie.domain.linkingtable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;

@Entity(primaryKeys = {"genreId", "mediaId"}, tableName="genre_media_table")
public class GenreMedia {
    @NonNull
    private int genreId;
    @NonNull
    private int mediaId;

    public GenreMedia(int genreId, int mediaId) {
        this.genreId = genreId;
        this.mediaId = mediaId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }
}
