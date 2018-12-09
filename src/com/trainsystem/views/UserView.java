package com.trainsystem.views;

import com.trainsystem.models.User;

public class UserView {

    public static void showUser(User user) {
        System.out.println("ID: " + user.getId() + " " + user.getUsername() + ": " + user.getRole());
    }
}
