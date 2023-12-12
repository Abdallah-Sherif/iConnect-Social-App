package com.example.iconnect.Entities;


import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private LocalDate birthdate;
    private String gender;
    private List<User> friends;
    private List<User> sentFriendRequests;
    private List<User> receivedFriendRequests;
    private List<Notification> CurrentNotifications;
    public List<Post> posts= new ArrayList<>();
   
    
    public List<Notification> getCurrentNotifications() {
        return CurrentNotifications;
    }
    //****
    public void addNotifications(Notification newNotification) {
        CurrentNotifications.add(newNotification);
    }

    public User(String username,String password,String Email,String Gender,LocalDate BirthDate) {
        this.username = username;
        this.password = password;
        this.email = Email;
        this.gender = Gender;
        this.birthdate = BirthDate;
        this.friends = new ArrayList<>();
        this.sentFriendRequests = new ArrayList<>();
        this.receivedFriendRequests = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public List<User> getFriends() {       
        return friends;
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    public List<User> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public void addSentFriendRequest(User friend) {
        sentFriendRequests.add(friend);
    }

    public void removeSentFriendRequest(User friend) {
        sentFriendRequests.remove(friend);
    }

    public List<User> getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    public void addReceivedFriendRequest(User friend) {
        receivedFriendRequests.add(friend);
    }

    public void removeReceivedFriendRequest(User friend) {
        receivedFriendRequests.remove(friend);
    }

    
    //*************************************************************************
    //button
    public void sendPostNotification()
    {
       Notification notification=new Notification(this.username+" send a post");
       for(User friend:friends)
       {
            friend.CurrentNotifications.add(notification);
       }
    }
    public List<Post> getPosts() {
        return posts;
    }
    
    //button
    public void addPost(Post post) {
        posts.add(post);
        //after sending post other friends should recieve notification
        //sendPostNotification();
    }
    
    //button
    public void removePost(Post post) {
        posts.remove(post);
    }
    public void clearPosts()
    {
        posts.clear();
    }
    
    //button
    public void createPost(String content, User get, boolean par,String imageUrl,List<User> TaggedUsers) {
        Post post = new Post(content, this,par,imageUrl,TaggedUsers);
        addPost(post);
    }
    
    /*public void creatpost_image(String image)
    {
        Post post = new Post(image, this);
        addPost(post);
    }*/

    public void removePost(int postId) {
        Post post = getPosts().get(postId);
        removePost(post);
    }
   


    
    Object getNotifications() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
