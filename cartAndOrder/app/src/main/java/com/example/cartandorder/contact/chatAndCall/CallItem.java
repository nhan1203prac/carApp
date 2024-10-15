package com.example.cartandorder.contact.chatAndCall;

import java.time.LocalDateTime;

public class CallItem {

    private String name;
    private LocalDateTime time;
    private boolean isSend;
    private String phoneNumber;
    private String image;

    public CallItem(String name,LocalDateTime time,boolean isSend, String phoneNumber, String image) {
        this.name = name;
        this.time = time;
        this.isSend = isSend;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public boolean isSend() {
        return isSend;
    }
}
