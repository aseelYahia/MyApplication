package com.example.myapplication;

public class Song {
    String Album;
    String name;

    public Song(String album, String name) {
        Album = album;
        this.name = name;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

