package com.example.iconnect.Entities;
import java.io.Serializable;
import com.example.iconnect.Entities.User;
import java.time.LocalDateTime;

public class Notification implements Serializable{

    private String message;
    private LocalDateTime timestamp;

    public Notification(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
