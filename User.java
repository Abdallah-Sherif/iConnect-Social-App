package com.example.iconnect.Entities;


import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * The {@code User} class represents a user in a social networking application.
 * It encapsulates information about the user, including their username,
 * password, email, birthdate, gender, friends, notifications, posts, and profile image.
 *
 * <p>The class provides methods to manage the user's interactions, such as adding and
 * removing friends, sending and receiving friend requests, handling notifications,
 * and managing posts.
 *
 * <p>Instances of this class can be serialized using the {@link java.io.Serializable}
 * interface, making it suitable for storage and network transmission.
 *
 * @author AndElRahman
 */

public class User implements Serializable {

    private String username;
    private String password;
    private String email;

    public LocalDate getBirthdate() {
        return birthdate;
    }

    private LocalDate birthdate;

    public String getGender() {
        return gender;
    }

    private String gender;
    private List<User> friends;
    private List<User> sentFriendRequests;
    private List<User> receivedFriendRequests;

    public List<User> getRestrictedFriends() {
        return RestrictedFriends;
    }

    private List<User> RestrictedFriends;
    private List<Notification> CurrentNotifications = new ArrayList<>();
    public List<FriendRequestNotification> getCurrentRequestNotifications() {
        return currentRequestNotifications;
    }

    private List<FriendRequestNotification> currentRequestNotifications = new ArrayList<>();
    private List<TaggedUserNotification> taggedUserNotifications = new ArrayList<>();
    public List<Post> posts= new ArrayList<>();
    private String ProfileImagePath;

    /**
     * Gets the notifications for the user.
     *
     * @return A list of Notification objects representing current notifications.
     */
    public List<Notification> getCurrentNotifications() {
        return CurrentNotifications;
    }
    /**
     /**
     * remove a notification to the user's current notifications.
     *
     * @param notification The notification to be removed.
     */
    public void RemoveNotification(Notification notification)
    {
        CurrentNotifications.remove(notification);
    }
    //****
    /**
     /**
     * Adds a notification to the user's current notifications.
     *
     * @param newNotification The notification to be added.
     */
    public void addNotifications(Notification newNotification) {
        CurrentNotifications.add(newNotification);
    }
    // Class fields and attributes...

    /**
     * Constructs a new {@code User} with the specified parameters.
     *
     * @param username      The username of the user.
     * @param password      The password of the user.
     * @param Email         The email address of the user.
     * @param Gender        The gender of the user.
     * @param BirthDate     The birthdate of the user.
     * @param ImagePath     The file path to the user's profile image.
     */
    public User(String username,String password,String Email,String Gender,LocalDate BirthDate,String ImagePath) {
        this.username = username;
        this.password = password;
        this.email = Email;
        this.gender = Gender;
        this.birthdate = BirthDate;
        this.friends = new ArrayList<>();
        this.sentFriendRequests = new ArrayList<>();
        this.receivedFriendRequests = new ArrayList<>();
        ProfileImagePath = ImagePath;
        this.CurrentNotifications = new ArrayList<>();
        this.currentRequestNotifications = new ArrayList<>();
        this.taggedUserNotifications = new ArrayList<>();
        this.RestrictedFriends = new ArrayList<>();
    }
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * Gets the list of friends associated with the user.
     *
     * @return A List of User objects representing the friends of the user.
     */
    public List<User> getFriends() {       
        return friends;
    }
    /**
     * Adds a user to the list of restricted friends.
     *
     * @param user The user to be added to the restricted friends list.
     */
    public void addRestrictedUser(User user)
    {
        RestrictedFriends.add(user);
    }
    /**
     * Removes a user from the list of restricted friends.
     *
     * @param user The user to be removed from the restricted friends list.
     */
    public void removeRestrictedUser(User user)
    {
        RestrictedFriends.remove(user);
    }
    /**
     * Removes the specified user from the list of friends of the current user.
     *
     * @param friend The User object representing the friend to be removed.
     * @throws NullPointerException if the provided friend is null.
     * @return true if the friend was successfully removed, false otherwise.
     * */
    public void removeFriend(User friend) {
        if (friend == null) {
            throw new NullPointerException("Friend cannot be null");
        }
        friends.remove(friend);
    }
    /**
     * Retrieves the list of friend requests sent by the current user.
     *
     * @return A List<User> representing friend requests sent by the current user.
     */
    public List<User> getSentFriendRequests() {
        return sentFriendRequests;
    }
    /**
     * Retrieves the list of add friend requests sent by the current user.
     *
     * @return A List<User> representing adding friend requests sent by the current user.
     */
    public void addSentFriendRequest(User friend) {
        if (friend == null) {
            throw new NullPointerException("Friend cannot be null");
        }
        if (sentFriendRequests.contains(friend)) {
            throw new IllegalArgumentException("Friend is already in the list of sent friend requests");
        }
        sentFriendRequests.add(friend);
    }
    /**
     * Removes the specified user from the list of sent friend requests by the current user.
     *
     * @param friend The User object representing the friend for whom  sent request is to be canceled.
     * @throws NullPointerException if the provided friend is null.
     * @return true if the friend request was successfully removed, false otherwise.
     */
    public void removeSentFriendRequest(User friend) {
        if (friend == null) {
            throw new NullPointerException("Friend cannot be null");
        }
        sentFriendRequests.remove(friend);
    }
    /**
     * Retrieves the list of friend requests received by the current user.
     *
     * @return A List<User> representing friend requests received by the current user.
     */
    public List<User> getReceivedFriendRequests() {
        return receivedFriendRequests;
    }
    /**
     * Retrieves the list of add friend requests received by the current user.
     *
     * @return A List<User> representing adding friend requests received by the current user.
     */
    public void addReceivedFriendRequest(User friend) {
        receivedFriendRequests.add(friend);
    }
    /**
     * Removes the specified user from the list of received friend requests by the current user.
     *
     * @param friend The User object representing the friend for whom the received request is to be declined.
     * @throws NullPointerException if the provided friend is null.
     * @return true if the friend request was successfully declined, false otherwise.
     */
    public void removeReceivedFriendRequest(User friend) {
        if (friend == null) {
            throw new NullPointerException("Friend cannot be null");
        }
        receivedFriendRequests.remove(friend);
    }

    
    //*************************************************************************
    //button
    //button
    /**
     * Retrieves the list of posts created by the current user.
     *
     * @return A List<Post> representing the posts created by the current user.
     */
    public List<Post> getPosts() {
        return posts;
    }
    /**
     * Adds a post to the user's list of posts.
     *
     * The provided post is added to the user's list of posts, representing a new post created by the user.
     *
     * @param post The Post object to be added to the user's list of posts.
     */
    //button
    public void addPost(Post post) {
        posts.add(post);
        //after sending post other friends should recieve notification
        //sendPostNotification();
    }
    /**
     * Removes the specified post from the list of posts created by the current user.
     *
     * @param post The Post object to be removed from the user's list of posts.
     * @throws NullPointerException if the provided post is null.
     * @return true if the post was successfully removed, false otherwise.
     */
    //button
    public void removePost(Post post) {
        if (post == null) {
            throw new NullPointerException("Post cannot be null");
        }
        posts.remove(post);
    }
    /**
     * Clears all posts created by the current user.
     */
    public void clearPosts()
    {
        posts.clear();
    }
    /**
     * Creates and adds a new post to the user's list of posts.
     *
     * This method facilitates the creation of a new post with the specified content, privacy settings,
     * image URL, and tagged users. The newly created post is then added to the user's list of posts.
     *
     * @param content The content of the post.
     * @param TaggedUsers The User object representing the target user for the post.
     * @param par A boolean indicating the privacy setting of the post. True for public, false for private.
     * @param imageUrl The URL of the image attached to the post.
     * @param TaggedUsers A List<User> representing users tagged in the post.
     * @throws NullPointerException if the content parameter is null.
     */
    //button
    public void createPost(String content, User get, boolean par,String imageUrl,List<User> TaggedUsers) {
        if (content == null) {
            throw new NullPointerException("Content cannot be null");
        }
        Post post = new Post(content, this,par,imageUrl);
        addPost(post);
    }
    /**
     * Removes a post at the specified index from the user's list of posts.
     *
     * This method identifies the post at the specified index in the user's list of posts and removes it.
     * The index corresponds to the position of the post in the list.
     *
     * @param postId The index of the post to be removed.
     */
    public void removePost(int postId) {
        Post post = getPosts().get(postId);
        removePost(post);
    }


    /**
     * Gets the notifications for the user.
     *
     * @return A list of Notification objects representing current notifications.
     */

    Object getNotifications() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    /**
     * Gets the path to the user's profile image.
     *
     * @return The profile image path.
     */
    public String getProfileImagePath() {
        return ProfileImagePath;
    }
    /**
     * Sets the path to the user's profile image.
     *
     * @param profileImagePath The new profile image path.
     */
    public void setProfileImagePath(String profileImagePath) {
        ProfileImagePath = profileImagePath;
    }
    /**
     * Retrieves a friend by their username.
     *
     * @param name The username of the friend to retrieve.
     * @return The User object representing the friend with the specified username, or null
     *         if no such friend is found.
     */
    public User returnFriend(String name) {
        for (User friend : friends) {
            if (friend.getUsername().equals(name)) {
                return friend;


            }
        }
        return null;
    }
}
