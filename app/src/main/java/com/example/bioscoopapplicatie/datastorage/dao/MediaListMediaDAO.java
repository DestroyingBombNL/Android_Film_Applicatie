package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */
@Dao
public interface MediaListMediaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MediaListMedia mediaListMedia);
}