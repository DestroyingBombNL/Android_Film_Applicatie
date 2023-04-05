package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bioscoopapplicatie.domain.AuthorDetail;
import com.example.bioscoopapplicatie.domain.MediaList;
import com.example.bioscoopapplicatie.domain.Review;

import java.util.List;

/**
 * Data Access Object (DAO) for a word.
 * Each method performs a database operation, such as inserting or deleting a word,
 * running a DB query, or deleting all words.
 */
@Dao
public interface ReviewDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Review review);

    @Query("SELECT * FROM review_table")
    LiveData<List<Review>> getAllReviews();

    @Query("SELECT * FROM review_table WHERE mediaId = :filter")
    LiveData<List<Review>> getFilteredReviews(String filter);

    @Query("UPDATE review_table SET mediaId = :mediaId WHERE id = :id")
    void updateReview(String mediaId, String id);
}
