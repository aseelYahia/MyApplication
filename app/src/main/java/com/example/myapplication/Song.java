package com.example.myapplication;

public class Song {
    String Album;
    String name;
    int idx;
    public Song(String album, String name) {
        this.Album = album;
        this.name = name;
        this.idx=-1;
    }

    public Song(String album, String name, int idx) {
        Album = album;
        this.name = name;
        this.idx = idx;
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

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "Song{" +
                "Album='" + Album + '\'' +
                ", name='" + name + '\'' +
                ", idx=" + idx +
                '}';
    }
}


