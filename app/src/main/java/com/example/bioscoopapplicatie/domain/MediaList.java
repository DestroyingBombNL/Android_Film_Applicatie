package com.example.bioscoopapplicatie.domain;

import java.util.ArrayList;
import java.util.List;

public class MediaList {
    private int id;
    private String name;
    private String creationDate;
    private List<Media> mediaList;
    private com.example.bioscoopapplicatie.domain.User user;

    public MediaList(int id, String name, String creationDate, com.example.bioscoopapplicatie.domain.User user, List<Media> mediaList) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.user = user;
        this.mediaList = new ArrayList<>();
    }

    public MediaList(String name, String creationDate, com.example.bioscoopapplicatie.domain.User user, List<Media> mediaList) {
        this.name = name;
        this.creationDate = creationDate;
        this.user = user;
        this.mediaList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public com.example.bioscoopapplicatie.domain.User getUser() {
        return user;
    }

    public void setUser(com.example.bioscoopapplicatie.domain.User user) {
        this.user = user;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
