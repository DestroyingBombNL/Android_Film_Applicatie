package com.example.bioscoopapplicatie.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"mediaListId", "mediaId"}, tableName="media_list_media_table")
public class MediaListMedia {
    @NonNull
    private int mediaListId;
    @NonNull
    private int mediaId;

    public MediaListMedia(int mediaListId, int mediaId) {
        this.mediaListId = mediaListId;
        this.mediaId = mediaId;
    }

    public int getMediaListId() {
        return mediaListId;
    }

    public void setMediaListId(int mediaListId) {
        this.mediaListId = mediaListId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }
}
