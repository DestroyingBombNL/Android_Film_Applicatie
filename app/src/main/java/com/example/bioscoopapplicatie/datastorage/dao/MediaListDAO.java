package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bioscoopapplicatie.domain.Genre;
import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.MediaList;

import java.util.List;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */
@Dao
public interface MediaListDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MediaList mediaList);

    @Query("SELECT * FROM media_list_table ORDER BY id DESC")
    LiveData<List<MediaList>> getAllMediaLists();

    @Query("UPDATE media_list_table SET id = :value WHERE name = :name")
    void updateMediaList(String value, String name);

    @Delete
    void deleteList(MediaList mediaList);
}