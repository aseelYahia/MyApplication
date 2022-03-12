package com.example.myapplication;

public class User {

    private String name;
    private String email;
    private String img;
    private String fav_album;
    private int score;
    private int num_friends;

    public User(String name, String email,  String fav_album) {
        this.name = name;
        this.email = email;
        this.img = "no pic yet";
        this.fav_album = fav_album;
        this.score = 0;
        this.num_friends = 0;
    }
    public User(User u,String img)
    {
        this.name = u.getName();
        this.email = u.getEmail();
        this.img = img;
        this.fav_album = u.getFav_album();
        this.score = u.getScore();
        this.num_friends =u.getNum_friends();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFav_album() {
        return fav_album;
    }

    public void setFav_album(String fav_album) {
        this.fav_album = fav_album;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNum_friends() {
        return num_friends;
    }

    public void setNum_friends(int num_friends) {
        this.num_friends = num_friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                ", fav_album='" + fav_album + '\'' +
                ", score=" + score +
                ", num_friends=" + num_friends +
                '}';
    }
}
