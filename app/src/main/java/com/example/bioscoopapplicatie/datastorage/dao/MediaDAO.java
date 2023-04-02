package com.example.bioscoopapplicatie.datastorage.dao;

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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Media media);
    @Query("SELECT * from media_table ORDER BY original_title ASC")
    LiveData<List<Media>> getAllMedia();
    @Query("SELECT id from media_table ORDER BY id ASC")
    LiveData<List<Integer>> getAllMediaId();
    @Query("SELECT * from media_table WHERE original_title LIKE :filter")
    LiveData<List<Media>> getAllFilteredMedia(String filter);
}
