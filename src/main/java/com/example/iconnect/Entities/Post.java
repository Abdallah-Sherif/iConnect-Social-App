package com.example.iconnect.Entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Post implements Serializable{

    private String content;
    private User author;
    private LocalDateTime timestamp;
    
    private byte[] imageData;//private String image;
    private int Post_ID;
    private static int UniversalID=0;
    private boolean Privacy = true;
    
    private List<User> likes;
    private List<Comment> comments;
    
    List<Post> randomPost=new ArrayList<>();
    
    public Post(String content, User author,boolean isPrivate) {
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        Post_ID = UniversalID;
        Privacy = isPrivate;
        //this.image = null;
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
    
    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
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
