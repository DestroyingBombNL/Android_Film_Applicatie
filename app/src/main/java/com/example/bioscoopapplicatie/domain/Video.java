package com.example.bioscoopapplicatie.domain;

import java.util.List;

public class Video {
    private int id;
    private List<Result> results;

    public int getId() {
        return id;
    }

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private String iso_639_1;
        private String iso_3166_1;
        private String name;
        private String key;
        private String site;
        private int size;
        private String type;
        private boolean official;
        private String published_at;
        private String id;

        public String getIso_639_1() {
            return iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public String getSite() {
            return site;
        }

        public int getSize() {
            return size;
        }

        public String getType() {
            return type;
        }

        public boolean isOfficial() {
            return official;
        }

        public String getPublished_at() {
            return published_at;
        }

        public String getId() {
            return id;
        }
    }
}
