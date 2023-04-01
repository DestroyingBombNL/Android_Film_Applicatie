package com.example.bioscoopapplicatie.domain;

import java.util.ArrayList;
import java.util.List;

public class DataGenre {
    public static List<Genre> getGenreList(){
        List<Genre> genreList = new ArrayList<>();

        Genre noGenre = new Genre();
        noGenre.setName("No filter");
        genreList.add(noGenre);

        Genre action = new Genre();
        action.setName("Action");
        genreList.add(action);

        Genre comedy = new Genre();
        comedy.setName("Comedy");
        genreList.add(comedy);

        return genreList;
    }
}
