package com.example.myapplication;

public class Album {
    private String name;
    private String date;
    private boolean isTV;
    private String[] songs;

    public Album(){

    }
    public Album(String name,String date,boolean isTV,String[] songs){
        this.name=name;
        this.date=date;
        this.isTV=isTV;
        this.songs=new String[songs.length];
        for (int i=0;i<songs.length;i++)
        {
            this.songs[i]=songs[i];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
