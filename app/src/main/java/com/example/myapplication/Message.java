package com.example.myapplication;

public class Message {
    private String chat_name;
    private User sender;
    private String text;
    public Message(String chat_name, User sender, String text)
    {
        this.chat_name=chat_name;
        this.sender=sender;
        this.text=text;
    }
    public Message()
    {

    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }
}
