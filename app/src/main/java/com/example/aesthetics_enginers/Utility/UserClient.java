package com.example.aesthetics_enginers.Utility;
import android.app.Application;
import com.example.aesthetics_enginers.Models.User;


public class UserClient extends Application{
    private User user = null;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
