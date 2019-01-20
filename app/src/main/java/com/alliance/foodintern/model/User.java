package com.alliance.foodintern.model;

public class User {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String userName, password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
