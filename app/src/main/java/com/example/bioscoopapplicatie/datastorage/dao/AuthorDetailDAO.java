package com.example.bioscoopapplicatie.datastorage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bioscoopapplicatie.domain.AuthorDetail;

import java.util.List;

public interface AuthorDetailDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AuthorDetail authorDetail);
    @Query("SELECT * from author_details_table")
    LiveData<List<AuthorDetail>> getAllAuthorDetails();
}
