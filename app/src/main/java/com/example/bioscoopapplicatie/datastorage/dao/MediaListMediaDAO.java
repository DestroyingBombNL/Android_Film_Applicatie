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
    void insertMediaToList(MediaListMedia mediaListMedia);

    @Query("SELECT media_table.* " +
            "FROM media_table " +
            "JOIN media_list_media_table ON media_table.id = media_list_media_table.mediaId " +
            "JOIN media_list_table ON media_list_media_table.mediaListId = media_list_table.id " +
            "WHERE media_list_table.id = :listId")
    LiveData<List<Media>> getAllMediaInList(String listId);
    //    getAllFilteredMediaByGenre
    @Query("SELECT mt.* FROM media_table mt " +
            "INNER JOIN media_list_media_table mlt ON mt.id = mlt.mediaId " +
            "INNER JOIN genre_media_table gmt ON mt.id = gmt.mediaId " +
            "INNER JOIN genre_table gt ON gmt.genreId = gt.id " +
            "WHERE gt.id = :genreId")
    LiveData<List<Media>> getAllFilteredMediaListByGenre(int genreId);

    @Query("DELETE FROM media_list_media_table WHERE mediaListId = :mediaListId")
    void deleteListFromMediaListMedia(int mediaListId);
}