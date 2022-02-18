package com.example.myapplication;

public class Lyric {
    String title;
    String text;
    User user;
    public Lyric(String title,String text,User user)
    {
        this.title=title;
        this.text=text;
        this.user=user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
