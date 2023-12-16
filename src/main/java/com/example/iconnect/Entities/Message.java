package com.example.iconnect.Entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    private User sender;
    private String currentDateTime;
    private String content;

    public Message(User sender, String content) {

        this.sender = sender;
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDate.format(formatter);
        this.currentDateTime = formattedDateTime;
        this.content = content;
    }



    public User getSender() {
        return sender;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public String getContent() {
        return content;
    }
    public void displayMessage(){
        System.out.println(getSender().getUsername()+":"+getContent());
    }


}
