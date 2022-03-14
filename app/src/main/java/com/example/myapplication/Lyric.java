package com.example.myapplication;

public class Lyric {
    String title;
    String text;

    public Lyric(String title,String text)
    {
        this.title=title;
        this.text=text;


    }
    public Lyric(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
