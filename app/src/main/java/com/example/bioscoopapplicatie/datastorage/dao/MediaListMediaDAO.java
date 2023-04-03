package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bioscoopapplicatie.domain.Media;
import com.example.bioscoopapplicatie.domain.linkingtable.MediaListMedia;

import java.util.List;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */
@Dao
public interface MediaListMediaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MediaListMedia mediaListMedia);

    @Query("SELECT media_table.* " +
            "FROM media_table " +
            "JOIN media_list_media_table ON media_table.id = media_list_media_table.mediaId " +
            "JOIN media_list_table ON media_list_media_table.mediaListId = media_list_table.id " +
            "WHERE media_list_table.id = :listId")
    LiveData<List<Media>> getAllMediaInList(String listId);

}