package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.linkingtable.GenreMedia;

import java.util.List;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */
@Dao
public interface GenreMediaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GenreMedia genreMedia);

    @Query("SELECT * from genre_media_table")
    LiveData<List<GenreMedia>> getAllGenreMedia();
}