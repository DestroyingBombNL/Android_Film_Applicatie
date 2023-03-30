package com.example.bioscoopapplicatie.domain;

import java.security.IdentityScope;
import java.util.ArrayList;
import java.util.List;

public class MediaList {
    private int id;
    private String name;
    private String description;
    private String language;
    private List<Integer> mediaList = new ArrayList<>();;

    public MediaList(int id, String name, String description, String language, List<Integer> mediaList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.mediaList = mediaList;
    }

    public MediaList(String name, String description, String language, List<Integer> mediaList) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.mediaList = mediaList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    public List<Integer> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Integer> mediaList) {
        this.mediaList = mediaList;
    }
}
