package com.example.iconnect.Entities;
import javafx.scene.image.Image;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;
import javax.imageio.ImageIO;


public class Post implements Serializable{

    private String content;
    private User author;
    private LocalDateTime timestamp;

    private String imageUrl;
    private int Post_ID;
    private static int UniversalID=0;
    private boolean Privacy = true;
    
    private List<User> likes = new ArrayList<>();

    public List<User> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(List<User> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    private List<User> taggedUsers = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    
    List<Post> randomPost = new ArrayList<>();
    
    public Post(String content, User author,boolean isPrivate,String imageUrl,List<User> taggedusers) {
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.imageUrl = imageUrl;
        Post_ID = UniversalID;
        Privacy = isPrivate;
        this.taggedUsers.addAll(taggedusers);
        UniversalID++;
        
    }
    public boolean getPrivacy()
    {
        return Privacy;
    }
    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getImageData() {
        return imageUrl;
    }

    public void setImageData(String image) throws IOException {
        imageUrl = image;
    }
    
    
    
    public void editContent(String newContent) {
        this.content = newContent;
    }
  
    /*public void uploadImage(String imagePath) {
        this.image = imagePath;
    }

    public String getImage() {
        return image;
    }*/
    
    
    //************************************************************
    
    public List<User> getLikes() {
        return likes;
    }
    //button
    public void addLike(User user) {
        if (!likes.contains(user)) {
            likes.add(user);
            sendLikeNotification(user);
        }
        
    }
    //button
       public void sendLikeNotification(User friend)
    {
       Notification notification=new Notification(friend.getUsername()+" added like to your post");
       author.addNotifications(notification);
       
    }
       
    //comment textbox
       public void sendCommentNotification(User friend,Comment comment)
    {
       Notification notification=new Notification(friend.getUsername()+" "+comment.getContent());
       author.addNotifications(notification);
    }
    
    
    public void removeLike(User user) {
        likes.remove(user);
    }
    
    
    public List<Comment> getComments() {
        return comments;
    }
// edit it
    public void addComment(Comment comment) {
       
    }
}
