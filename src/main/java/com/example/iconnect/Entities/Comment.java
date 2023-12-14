
package com.example.iconnect.Entities;
import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comment implements Serializable {

    private Post post;
    private User user;
    private String content;
    private List<User> likes = new ArrayList<>();
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

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
    public void removeLike(User user) {
        likes.remove(user);
    }
    public void addLike(User user) {
        if (!likes.contains(user)) {
            likes.add(user);
            //sendLikeNotification(user);
        }

    }
}
