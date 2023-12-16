/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.iconnect.FileManagement;

import com.example.iconnect.Entities.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HilaD
 */
public class UserManager {
    public static List<User> users = new ArrayList<>();
    public static User curr_user;
    public static Image curr_user_profile;
    protected static String usersFilePath = "users.dat";
    protected static String ChatsFilePath = "chats.dat";
    public static List<Conversation> chats;

    public static void AddUser(User user)
    {
        users.add(user);
    }
    public void ClearUsers()
    {
        users.clear();
    }

    public static void setCurr_user_profile(Image image)
    {
        curr_user_profile = image;
    }
    
     public static void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(usersFilePath))) {
            out.writeObject(users);
        } catch (IOException e) {
        }
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ChatsFilePath))) {
             out.writeObject(chats);
         } catch (IOException e) {
         }
     }

    public static void loadUsers() {
        users.clear();
        chats.clear();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(usersFilePath))) {
            users = (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ChatsFilePath))) {
            chats = (List<Conversation>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public static void DisplayUsers()
    {
        for(User user : users)
        {
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            if(user.getPosts() != null)
            {
             System.out.println(user.getPosts().size());   
            }
        }
        System.out.println("done");
    }
    public static List<Post> getPostsToLoad()
    {
        List<Post> posts = new ArrayList<>();
        for(User user : users)
        {
            if(user.equals(curr_user)) continue;
            for(Post post : user.getPosts())
            {
                if(!post.getPrivacy() || user.getFriends().contains(curr_user))
                {
                    posts.add(post);
                }
            }
        }
        return posts;
    }
    public static User IsUser(String Username,String Password)
    {
        for(User user: users)
        {
            if(Username.equals(user.getUsername())&&Password.equals(user.getPassword()))
            {
                curr_user = user;
                return user;
            }
        }
        return null;
    }
    public static void SendFriendRequest(User Sender, User Reciever) {
        // Check if the users are already friends
        if (Sender.getFriends().contains(Reciever)) {
            System.out.println("Users are already friends");
            return;
        }

        // Check if the friend request has already been sent
        if (Sender.getSentFriendRequests().contains(Reciever)) {
            System.out.println("Friend request already sent");
            return;
        }
        
        Reciever.getReceivedFriendRequests().add(Sender);

        // Add the current user to the friend's friend list
        Sender.getSentFriendRequests().add(Reciever);
        sendRequestNotification(Sender,Reciever);

        //sendRequestNotification( Sender, Reciever);
    }
     public static void acceptFriendRequest(User Sender, User Reciever) {
        // Check if the friend request exists
        if (!Sender.getSentFriendRequests().contains(Reciever)) {
            System.out.println("Friend request not found");
            return;
        }
        if(Sender.getFriends().contains(Reciever))
        {
            System.out.println("AlreadyFriends");
            return;
        }

        Reciever.getReceivedFriendRequests().remove(Sender);
        Sender.getSentFriendRequests().remove(Reciever);
        // Add the friend to the current user's friend list
        Sender.getFriends().add(Reciever);

        // Add the current user to the friend's friend list
        Reciever.getFriends().add(Sender);
        sendAcceptNotification(Sender,Reciever);

        // Send a friend confirmation notification to the friend
        //****
        //sendAcceptNotification(Sender,Reciever);
    }

    public static void declineFriendRequest(User Sender, User Reciever) {
        // Check if the friend request exists
        if (!Reciever.getReceivedFriendRequests().contains(Sender)) {
            System.out.println("Friend request not found");
            return;
        }
        Reciever.getReceivedFriendRequests().remove(Sender);
        Sender.getSentFriendRequests().remove(Reciever);

        // Send a friend request declined notification to the friend

    }
    
    public static void sendRequestNotification(User sender,User reciever)
    {
        Notification notification = new FriendRequestNotification(sender.getUsername()+" send you a friend request",sender);
        reciever.addNotifications(notification);
    }
      
       
    public static void sendAcceptNotification(User sender,User reciever)
    {
        Notification notification = new FriendRequestNotification(reciever.getUsername()+" accepted your request",reciever);
       sender.addNotifications(notification);
    }
    
    
       //*************************************************************************
    
        public List<Post> getFriendPosts(User user) {
        List<Post> friendPosts = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendPosts.addAll(friend.getPosts());
        }
        return friendPosts;
    }

    public List<Post> getAllPosts(User user) {
        List<Post> allPosts = new ArrayList<>();
        allPosts.addAll(user.getPosts());
        allPosts.addAll(getFriendPosts(user));
        return allPosts;
    }
    
    public List<Post> getRandomPosts(List<Post>ShuffeledPosts) {
        
        // Shuffle the posts
        Collections.shuffle(ShuffeledPosts);

        // Return the shuffled posts
        return ShuffeledPosts;
    }

   
    
    //*******************************************************************
                
    public int getNumberOfLikes(Post post) {
        return post.getLikes().size();
    }

    public List<Comment> getComments(Post post) {
        return post.getComments();
    }
    
    
        // User interface updates
    // Display the number of likes on each post
    public void displayLikes(Post post) {
        int numberOfLikes = getNumberOfLikes(post);
        System.out.println("Number of likes: " + numberOfLikes);
    }

    // Display a list of comments on each post
    public void displayComments(Post post) {
        List<Comment> comments = getComments(post);
        for (Comment comment : comments) {
            System.out.println("Comment: " + comment.getContent());
        }
    }

       
}
