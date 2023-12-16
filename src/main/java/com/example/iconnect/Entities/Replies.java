package com.example.iconnect.Entities;

import java.io.Serializable;

public class Replies extends Text implements Serializable {
;

    public Replies(User userReplys, String content) {
        super(content,userReplys);
    }

    public String UpdateContent(String newContent) {
        this.content = newContent;
        return content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public User getUser() {
        return user;
    }
}
