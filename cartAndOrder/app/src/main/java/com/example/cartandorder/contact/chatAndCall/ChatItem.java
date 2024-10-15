package com.example.cartandorder.contact.chatAndCall;

public class ChatItem {
    private String name;
    private String message;
    private String image;

    public ChatItem(String name, String message, String image) {
        this.name = name;
        this.message = message;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }
}
