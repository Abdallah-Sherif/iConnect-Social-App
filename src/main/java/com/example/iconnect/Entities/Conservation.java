package com.example.iconnect.Entities;

import java.util.ArrayList;
import java.util.List;

public class Conservation {
    public int Id;

    public String nameOfChat;
    public List<User>usersOfConcervation=new ArrayList<>();

    private List <Message> allMessages=new ArrayList<>();

    public Conservation(int id, User curr ,String nameOfChat) {
        Id = id;
        this.usersOfConcervation .add(curr);
        this.nameOfChat=nameOfChat;

    }

    public void sendMessage(User current, String message){
        Message currentMessage=new Message(current,message);
        allMessages.add(currentMessage);
    }
    public void setUsersOfConcervation(User user) {
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

}
