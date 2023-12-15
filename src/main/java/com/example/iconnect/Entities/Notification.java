package com.example.iconnect.Entities;
import java.io.Serializable;
import com.example.iconnect.Entities.User;
import java.time.LocalDateTime;

public abstract class Notification implements Serializable{

     protected String message;
     LocalDateTime timestamp;

    public User getSender() {
        return sender;
    }

    User sender;

    public Notification(String message,User sender) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.sender = sender;
    }

    public abstract String getMessage();

    public abstract LocalDateTime getTimestamp() ;
}
