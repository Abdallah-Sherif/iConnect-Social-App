
package com.example.iconnect.Entities;
import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;

import java.io.Serializable;

public class Comment implements Serializable {

    private Post post;
    private User user;
    private String content;

    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
    
    public void editContent(String newContent) {
        this.content = newContent;
    }
    
}
