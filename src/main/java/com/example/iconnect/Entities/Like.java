
package com.example.iconnect.Entities;

import com.example.iconnect.Entities.Post;
import com.example.iconnect.Entities.User;

public class Like {

    //private int postId;
    private User user;
    private Post post;
    
        public Like(User user,Post post  /*int postId*/) {
        this.user = user;
        this.post = post;
    }
    
    /*public int getPostId()
    {
       return postId;
    }*/
        
     public Post getPost() {
        return post;
    }
    
    public User getUser() {
        return user;
    }
    
}
