package com.example.iconnect.Entities;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    public static int Id;
    private int id;
    private String nameOfChat;

    public List<User> getUsersOfConversation() {
        return usersOfConcervation;
    }

    private List<User>usersOfConcervation=new ArrayList<>();

    public List<Message> getAllMessages() {
        return allMessages;
    }

    private List <Message> allMessages=new ArrayList<>();

    public Conversation(User curr , String nameOfChat) {
        this.usersOfConcervation .add(curr);
        this.nameOfChat=nameOfChat;
        id = Id;
        Id++;
    }

    public void sendMessage(User current, String message){
        Message currentMessage=new Message(current,message);
        allMessages.add(currentMessage);
    }
    public void addUser(User user) {
        if(usersOfConcervation.contains(user)){
            System.out.println("user is already exist");
        }
        else
          usersOfConcervation.add(user);
    }
    public void displayConservation(){
        for (Message item:allMessages) {
            item.displayMessage();
        }
    }
    public void showFriends(){
        for (User item:usersOfConcervation) {
            System.out.println(item.getUsername());
        }
    }
    public void DisplayFriend(User curr){
        for (User friend: curr.getFriends()) {
          if(!usersOfConcervation.contains(friend)){
              System.out.println(friend.getUsername());
          }

        }
    }

    public String getNameOfChat() {
        return nameOfChat;
    }
}
