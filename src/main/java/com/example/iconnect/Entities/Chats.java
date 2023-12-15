package com.example.iconnect.Entities;

import com.example.iconnect.FileManagement.UserManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Chats implements Serializable {
    Scanner scanner=new Scanner(System.in);
public static int counter=1;
public List<Conservation> allChats=new ArrayList<>();
public void createChat(User curruser){
    System.out.println("inter the name of the chat");

    String nameChat=scanner.next();
    Conservation conservation=new Conservation(counter,curruser,nameChat);
    allChats.add(conservation);
    counter++;

}
public void chatSearch(User current){
    for (Conservation show:allChats) {
        for (User user: show.usersOfConcervation) {
            if (current==user){
                System.out.println(show.Id+":"+show.nameOfChat);
            }
        }
    }
}
public void menu(int id){
    System.out.println("Enter 1 to show chat \n enter 2 to show friends of chat \n enter 3 to insert friend to chat ");
   int coun=scanner.nextInt();

    for (Conservation con:allChats) {
        if(id==con.Id){
            System.out.println();
            switch (coun){
                case  1:
                    con.displayConservation();
                    break;
                case 2:
                    con.showFriends();
                    break;
                case 3:
                    con.DisplayFriend(UserManager.curr_user);
                    String nameuser=scanner.next();
                    con.setUsersOfConcervation(UserManager.curr_user.returnFriend(nameuser));

            }
        }
    }
}
}
