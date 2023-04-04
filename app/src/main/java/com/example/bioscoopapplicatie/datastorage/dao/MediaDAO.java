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
    @Query("SELECT * FROM media_table WHERE id = :id")
    LiveData<Media> getMediaById(String id);
    @Query("SELECT * from media_table WHERE original_title LIKE '%' || :filter || '%'")
    LiveData<List<Media>> getAllFilteredMedia(String filter);

    //zou anders kunnen, maar werkt op een of andere manier niet.
    @Query("SELECT * from media_table ORDER BY vote_average DESC")
    LiveData<List<Media>> getAllOrderedVoteAverageMedia();
    @Query("SELECT * from media_table ORDER BY release_date DESC")
    LiveData<List<Media>> getAllOrderedReleaseDateMedia();

//    getAllFilteredMediaByGenre
    @Query("SELECT mt.* FROM media_table mt " +
            "INNER JOIN genre_media_table gmt ON mt.id = gmt.mediaId " +
            "INNER JOIN genre_table gt ON gmt.genreId = gt.id " +
            "WHERE gt.id = :genreId")
    LiveData<List<Media>> getAllFilteredMediaByGenre(int genreId);
}
