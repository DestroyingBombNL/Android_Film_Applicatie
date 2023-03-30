package com.example.bioscoopapplicatie.datastorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bioscoopapplicatie.domain.Media;

import java.util.List;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */

@Dao
public interface MediaDAO {
    @Query("SELECT * from media_table ORDER BY title ASC")
    LiveData<List<Media>> getAllMedia();

    @Query("SELECT * from media_table WHERE :filter")
    LiveData<List<Media>> getFilteredMedia(String filter);
}
